package com.tech.royal_vee.myapplication.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.tech.royal_vee.myapplication.AssignmentMessageView
import com.tech.royal_vee.myapplication.R

import com.tech.royal_vee.myapplication.model.Studentdetails

class StudentRecylerViewAdapter(
        private val studentsList: MutableList<Studentdetails>,
        private val context: Context,
        private val firestoreDB: FirebaseFirestore)
    : RecyclerView.Adapter<StudentRecylerViewAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentRecylerViewAdapter.ViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.list_student_maker, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val student = studentsList[position]

        holder!!.fullname.text = student.Fullname


        holder.fullname.setOnClickListener { veiwMessage(student) }

    }

    override fun getItemCount(): Int {
        return studentsList.size
    }


    inner class ViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        internal var fullname: TextView
        //internal var regnum: TextView
        //internal var edit: ImageView
        //internal var delete: ImageView

        init {
            fullname = view.findViewById(R.id.tv_Studentname)
            //regnum = view.findViewById(R.id.tvContent)

           // edit = view.findViewById(R.id.ivEdit)
           // delete = view.findViewById(R.id.ivDelete)
        }
    }

    private fun veiwMessage(student: Studentdetails) {
        val intent = Intent(context, AssignmentMessageView::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.putExtra("UpdateNoteId", student.id)
        context.startActivity(intent)
    }

   /* private fun deleteNote(id: String, position: Int) {
        firestoreDB.collection("notes")
                .document(id)
                .delete()
                .addOnCompleteListener {
                    notesList.removeAt(position)
                    notifyItemRemoved(position)
                    notifyItemRangeChanged(position, notesList.size)
                    Toast.makeText(context, "Note has been deleted!", Toast.LENGTH_SHORT).show()
                }
    }*/
}