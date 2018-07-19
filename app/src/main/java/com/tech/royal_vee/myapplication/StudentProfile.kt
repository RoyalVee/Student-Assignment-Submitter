package com.tech.royal_vee.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class StudentProfile : AppCompatActivity() {

    //initializing name textview
    lateinit  var dFullName: TextView

    //initializing email textview
    lateinit  var dEmail: TextView

    //initializing Regnumber textview
    lateinit  var dRegnumber: TextView

    private var mAuth: FirebaseAuth? = null

    private var db = FirebaseFirestore.getInstance()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_profile)


        dFullName = findViewById(R.id.StudentprofileName)
        dEmail = findViewById(R.id.StudentprofileEmail)
        dRegnumber = findViewById(R.id.StudentprofileRegNumber)
        mAuth = FirebaseAuth.getInstance()

        displayUserProfile()
    }

    private fun displayUserProfile() {
        /* val dbProfileName:String = intent.getStringExtra("dbfullname")
        val dbProfileEmail:String = intent.getStringExtra("dbEmail")
        val dbProfileRegNumber:String = intent.getStringExtra("dbRegNumber")*/

        //call the current registeres user
        val currentUser = mAuth!!.currentUser

        //getting the unique user id which is to be used for saving data to firestore as document
        val userAuthID = currentUser!!.uid

        db.collection("RegisteredUsers").document(userAuthID).get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val userdetails = task.result
                        var fields = StringBuilder("")
                        var dbName = userdetails.get("Fullname")
                        var dbEmail = userdetails.get("Email")
                        var dbRegNumber = userdetails.get("regnum")

                        dFullName.setText(dbName.toString())
                        dEmail.setText(dbEmail.toString())
                        dRegnumber.setText(dbRegNumber.toString())
                    }
                }
    }
}
