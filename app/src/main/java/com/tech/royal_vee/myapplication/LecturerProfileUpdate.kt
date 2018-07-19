package com.tech.royal_vee.myapplication

import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class LecturerProfileUpdate : AppCompatActivity() {

    lateinit var lecturerupdatefullnamename: EditText

    lateinit var lecturerupdateEmailmail: EditText

    lateinit var lecturerupdatePassword: EditText

    lateinit var lecturerupdateIDnumber: EditText

    private var db = FirebaseFirestore.getInstance()

    //progres dialog
    lateinit var progressDailog: ProgressDialog



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lecturer_profile_update)

        //initalize progress dialog
        progressDailog = ProgressDialog(this)

       lecturerupdatefullnamename = findViewById<EditText>(R.id.lectuerUpdatefullname)
        lecturerupdateEmailmail = findViewById<EditText>(R.id.lecturerUpdateEmail)
        lecturerupdatePassword = findViewById<EditText>(R.id.lecturerUpdatePassword)
        lecturerupdateIDnumber = findViewById<EditText>(R.id.lecturerIDNumber)

        var lecturerupdatesend = findViewById<Button>(R.id.sendUpdate)
        lecturerupdatesend.setOnClickListener {

            //progress dialog show
            progressDailog.setMessage("Updating Profile")
            progressDailog.show()

            //saving user infromation to firestore
            val users = HashMap<String, Any>()
            users.put("Fullname", lecturerupdatefullnamename.text.toString().trim())
            users.put("E-mail", lecturerupdateEmailmail.text.toString().trim())
            users.put("Paswword", lecturerupdatePassword.text.toString().trim())
            users.put("Idnumber", lecturerupdateIDnumber.text.toString().trim())

            db.collection("Lecturer").document("LecturerDoc").update(users)
                    .addOnSuccessListener {

                        //hiding progress bar
                        progressDailog.hide()
                        Toast.makeText(this, "Information Update Successful", Toast.LENGTH_LONG).show()
                    }
                    .addOnFailureListener {

                        //hiding progress bar
                        progressDailog.hide()
                        Toast.makeText(this, "upss....Information not Saved to Database", Toast.LENGTH_LONG).show()
                    }


            //Toast.makeText(this, "Update Sent", Toast.LENGTH_LONG).show()
        }
    }
}
