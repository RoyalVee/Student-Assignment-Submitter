package com.tech.royal_vee.myapplication

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.tech.royal_vee.myapplication.adapter.StudentRecylerViewAdapter
import com.tech.royal_vee.myapplication.model.Studentdetails
import kotlinx.android.synthetic.main.activity_assignment_list.*
import kotlinx.android.synthetic.main.activity_mainsec.*

class AssignmentList : AppCompatActivity() {
    private val TAG = "MainActivity"
    private var mAdapter: StudentRecylerViewAdapter? = null

    private var firestoreDB: FirebaseFirestore? = null
    private var firestoreListener: ListenerRegistration? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assignment_list)

        firestoreDB = FirebaseFirestore.getInstance()

        loadNotesList()

        firestoreListener = firestoreDB!!.collection("RegisteredUsers")
                .addSnapshotListener(EventListener { documentSnapshots, e ->
                    if (e != null) {
                        Log.e(TAG, "Listen failed!", e)
                        return@EventListener
                    }

                    val notesList = mutableListOf<Studentdetails>()

                    for (doc in documentSnapshots) {
                        val note = doc.toObject(Studentdetails::class.java)
                        note.id = doc.id
                        notesList.add(note)
                    }

                    mAdapter = StudentRecylerViewAdapter(notesList, applicationContext, firestoreDB!!)
                    tv_assignmentlist.adapter = mAdapter
                })






    }

    override fun onDestroy() {
        super.onDestroy()

        firestoreListener!!.remove()
    }

    private fun loadNotesList() {
        firestoreDB!!.collection("RegisteredUsers")
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val notesList = mutableListOf<Studentdetails>()

                        for (doc in task.result) {
                            val note = doc.toObject<Studentdetails>(Studentdetails::class.java)
                            note.id = doc.id
                            notesList.add(note)
                        }

                        mAdapter = StudentRecylerViewAdapter(notesList, applicationContext, firestoreDB!!)
                        val mLayoutManager = LinearLayoutManager(applicationContext)
                        tv_assignmentlist.layoutManager = mLayoutManager
                        tv_assignmentlist.itemAnimator = DefaultItemAnimator()
                        tv_assignmentlist.adapter = mAdapter
                    } else {
                        Log.d(TAG, "Error getting documents: ", task.exception)
                    }
                }
    }
}