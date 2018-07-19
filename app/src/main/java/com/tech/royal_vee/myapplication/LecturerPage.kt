package com.tech.royal_vee.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class LecturerPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lecturer_page)

        var lecstudentscore = findViewById<TextView>(R.id.lecturerpagescore)
        lecstudentscore.setOnClickListener {
            val intent = Intent(this, StudentScoreSheet::class.java)
            startActivity(intent)
        }

        var lecviewassignment = findViewById<TextView>(R.id.lecturerpageassignment)
        lecviewassignment.setOnClickListener {
            val intent = Intent(this, AssignmentList::class.java)
            startActivity(intent)
        }

        var lecLecturerprofile = findViewById<TextView>(R.id.lecturerpageprofileupdate)
        lecLecturerprofile.setOnClickListener {
            val intent = Intent(this, LecturerProfile::class.java)
            startActivity(intent)
        }
    }
}
