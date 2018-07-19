package com.tech.royal_vee.myapplication

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
    //declare loginemail input
    lateinit var loginEmail: EditText
    //declare loginpassword input
    lateinit var loginPassWord: EditText
    //progres dialog
    lateinit var progressDailog: ProgressDialog

    //Firebase authentication
    private var myAuth: FirebaseAuth? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login) //track if the user is aready login



            loginEmail = findViewById<EditText>(R.id.login_Email)
            loginPassWord = findViewById<EditText>(R.id.login_Password)

            //initalize progress dialog
            progressDailog = ProgressDialog(this)

            //initialization of authenication for firebase when oncreate is called
            myAuth = FirebaseAuth.getInstance()


            //login button to login
            val loginbtn = findViewById<Button>(R.id.login_btn)
            loginbtn.setOnClickListener {

                //progress dialog show
                progressDailog.setMessage("Login in Progress")
                progressDailog.show()

                //calling the userlogin method
                userLogin(loginEmail.text.toString().trim(), loginPassWord.text.toString().trim())


            }

            //button to take user to registration if not registered
            val loginRegisterbtn = findViewById<TextView>(R.id.login_Register)
            loginRegisterbtn.setOnClickListener {

                //closing the activity since the user wants register
                finish()

                //calling the registration activity
                val intent = Intent(this, Registration::class.java)
                startActivity(intent)
            }



    }




    private fun userLogin(emailentered: String, passwordentered: String) {

        if (emailentered.isEmpty() || passwordentered.isEmpty()) {

            //hiding progress bar
            progressDailog.hide()
            Toast.makeText(this, "Please enter your email and password", Toast.LENGTH_LONG).show()

        } else {

            myAuth!!.signInWithEmailAndPassword(emailentered, passwordentered)
                    .addOnCompleteListener(this) { task ->

                        if (task.isSuccessful) {

                            //hiding progress bar
                            progressDailog.hide()

                            //display this toast if the registration is succesful
                            Toast.makeText(applicationContext, "Succesfull login", Toast.LENGTH_LONG).show()

                            val intent = Intent(this, StudentPage::class.java)
                            startActivity(intent)
                        } else {
                            //hiding progress bar
                            progressDailog.hide()

                            //display this toast if the registration is succesful
                            Toast.makeText(applicationContext, "login Unsuccesfull... Please Try again", Toast.LENGTH_LONG).show()
                        }
                    }
        }
    }


}

