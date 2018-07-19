package com.tech.royal_vee.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var studentmainbtn = findViewById<Button>(R.id.mainstudentbtn)

        studentmainbtn.setOnClickListener{
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        var lecturermainbtn = findViewById<Button>(R.id.mainlecturerbtn)
        lecturermainbtn.setOnClickListener{
            val intent = Intent(this, LecturerLogin::class.java)
            startActivity(intent)
        }
    }
}
