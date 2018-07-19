package com.tech.royal_vee.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore

class LecturerProfile : AppCompatActivity() {

    private var db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lecturer_profile)

        val lecturerefullnametext = findViewById<TextView>(R.id.LecturerprofileName)
        val lecturereprofileEmailtext = findViewById<TextView>(R.id.lecturerprofileEmail)
        val lecturereprofileIDtext = findViewById<TextView>(R.id.lecturerprofileIDNumber)

        db.collection("Lecturer").document("LecturerDoc").get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val userdetails = task.result
                        var dbName = userdetails.get("Fullname")
                        var dbEmail = userdetails.get("E-mail")
                        var dbRegNumber = userdetails.get("Idnumber")

                        lecturerefullnametext.setText(dbName.toString())
                        lecturereprofileEmailtext.setText(dbEmail.toString())
                        lecturereprofileIDtext.setText(dbRegNumber.toString())
                    }
                }

        val lectureprofileupdatebtn = findViewById<Button>(R.id.lecturerProfileupdatebtn)
        lectureprofileupdatebtn.setOnClickListener {
            val intent = Intent(this, LecturerProfileUpdate::class.java)
            startActivity(intent)
        }
    }
}
