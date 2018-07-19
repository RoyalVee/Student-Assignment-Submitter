package com.tech.royal_vee.myapplication.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore
import com.tech.royal_vee.myapplication.AssignmentMessageView
import com.tech.royal_vee.myapplication.R
import com.tech.royal_vee.myapplication.model.Studentdetails
import kotlinx.android.synthetic.main.list_student_score_maker.view.*

class StudentScoreAdapter (
        private val studentsList: MutableList<Studentdetails>,
        private val context: Context,
        private val firestoreDB: FirebaseFirestore)
    : RecyclerView.Adapter<StudentScoreAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentScoreAdapter.ViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.list_student_score_maker, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val student = studentsList[position]

        holder!!.fullname.text = student.Fullname
        holder.regnum.text = student.regnum
        holder.score.text = student.Score



        //holder.fullname.setOnClickListener { veiwMessage(student) }

    }

    override fun getItemCount(): Int {
        return studentsList.size
    }


    inner class ViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        internal var fullname: TextView
        internal var regnum: TextView
        internal var score: TextView


        init {
            fullname = view.findViewById(R.id.tv_sheet_name)
            regnum = view.findViewById(R.id.tv_sheet_regnumber)
            score = view.findViewById(R.id.tv_sheet_Score)


        }
    }


}