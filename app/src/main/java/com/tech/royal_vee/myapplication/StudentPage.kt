package com.tech.royal_vee.myapplication

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class StudentPage : AppCompatActivity() {

    lateinit var assignmentSolution : TextView

    private var mAuth: FirebaseAuth? = null

    private var db = FirebaseFirestore.getInstance()

    //progres dialog
    lateinit var progressDailog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_page)

        //initalize progress dialog
        progressDailog = ProgressDialog(this)

        assignmentSolution = findViewById(R.id.assignmentSolution)

        mAuth = FirebaseAuth.getInstance()

        var studentpageprofile = findViewById<ImageView>(R.id.studentpageprofile)
        studentpageprofile.setOnClickListener{
            val intent = Intent(this, StudentProfile::class.java)
            startActivity(intent)
        }

        var studentpagesubmit = findViewById<ImageView>(R.id.studentpagesubmit)
        studentpagesubmit.setOnClickListener{

            //progress dialog show
            progressDailog.setMessage("Submitting Assignment")
            progressDailog.show()

            //call the current registeres user
            val currentUser = mAuth!!.currentUser

            //getting the unique user id which is to be used for saving data to firestore as document
            val userAuthID = currentUser!!.uid

            val users = HashMap<String, Any>()
            users.put("Message", assignmentSolution.text.toString())


            db.collection("RegisteredUsers").document(userAuthID).update(users)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {

                            //hiding progress bar
                            progressDailog.hide()

                            Toast.makeText(this, "Assignment Submitted", Toast.LENGTH_LONG).show()

                        }
                    }

        }
    }
}

