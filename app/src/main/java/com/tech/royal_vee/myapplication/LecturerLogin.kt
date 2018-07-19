package com.tech.royal_vee.myapplication

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class LecturerLogin : AppCompatActivity() {

    //declare loginemail input
    lateinit var lecloginEmail: EditText
    //declare loginpassword input
    lateinit var lecloginPassWord: EditText

    //progres dialog
    lateinit var progressDailog: ProgressDialog

    //declaring an instance for firestore
    private var db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lecturer_login)

        lecloginEmail = findViewById<EditText>(R.id.lecturer_login_Email)
        lecloginPassWord = findViewById<EditText>(R.id.lecturer_login_Password)

        //initalize progress dialog
        progressDailog = ProgressDialog(this)

        //login button to login
        val loginbtn = findViewById<Button>(R.id.login_btn)
        loginbtn.setOnClickListener {

            //progress dialog show
            progressDailog.setMessage("Login in Progress")
            progressDailog.show()

            //calling the userlogin method
            userLogin(lecloginEmail.text.toString().trim(), lecloginPassWord.text.toString().trim())


        }
    }

    private fun userLogin(email:String, password:String){
        db.collection("Lecturer").document("LecturerDoc").get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val userdetails = task.result
                        var dbEmail = userdetails.get("E-mail")
                        var dbPassword = userdetails.get("Password")

                        if(email == dbEmail.toString() && password == dbPassword.toString()){
                            //hiding progress bar
                            progressDailog.hide()

                            Toast.makeText(this, "Login Successful", Toast.LENGTH_LONG).show()

                            val intent = Intent(this, LecturerPage::class.java)
                            startActivity(intent)

                        }else{
                            //hiding progress bar
                            progressDailog.hide()

                            Toast.makeText(this, "Incorrect Email and Password", Toast.LENGTH_LONG).show()
                        }
                    }else{
                        //hiding progress bar
                        progressDailog.hide()

                        Toast.makeText(this, "DB fetch unsuccessful", Toast.LENGTH_LONG).show()
                    }
                }

    }
}
