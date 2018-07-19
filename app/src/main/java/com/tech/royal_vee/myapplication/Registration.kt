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
import com.google.firebase.firestore.FirebaseFirestore

class Registration : AppCompatActivity() {

    //public decalration for user entered name
    lateinit var regfullnamereg: EditText

    //public decalration for user entered email
    lateinit var regEmailreg: EditText

    //public decalration for user entered password
    lateinit var regPassWord: EditText

    //public decalration for user entered regNumber
    lateinit var regRegNum: EditText

    lateinit var textviewdis: TextView

    //making a progress bar for online reg indication
    lateinit var progressDialog: ProgressDialog

    //declaring the authentication call to firebase
    //with a null value parsed into it
    private var mAuth: FirebaseAuth? = null

    //declaring an instance for firestore
    private var db = FirebaseFirestore.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)


        //get the user fullname for registration
        regfullnamereg = findViewById<EditText>(R.id.reg_fullname)

        //get the user email for registration
        regEmailreg = findViewById<EditText>(R.id.reg_email)

        //get the user password for registration
        regPassWord = findViewById<EditText>(R.id.reg_password)

        //get the user school reg. Number
        regRegNum = findViewById<EditText>(R.id.reg_regnumber)

        //initialization of authenication for firebase when oncreate is called
        mAuth = FirebaseAuth.getInstance()

        progressDialog = ProgressDialog(this)


        //button for registration to database Auth
        //when this button is clicked the new user details is sent to the data base
        //and the the student activity page is opened
        var regRegisterclick = findViewById<Button>(R.id.btn_register)
        regRegisterclick.setOnClickListener {

            //when clicked calls the register method
            //parses in the email and password into the method from their various edittext
            registerToFirebase(regEmailreg.text.toString().trim(), regPassWord.text.toString().trim())

        }


    }


    //this method registers the email and password parsed into it
    fun registerToFirebase(emailentered: String, passwordentered: String) {

        //show progress dialog
        progressDialog.setMessage("Registering User......")
        progressDialog.show()

        //checking if user entered fullname and reg number
        var nameEnterChecker = regfullnamereg.text.toString().trim()
        var regNumberChecker = regRegNum.text.toString().trim()

        //checking if user entered required data before creating autnenication on firebase
        if (nameEnterChecker.isEmpty() || regNumberChecker.isEmpty()) {
            //hiding progress bar
            progressDialog.hide()
            Toast.makeText(this, "Please enter your Fullname and Registration Number", Toast.LENGTH_LONG).show()


        } else {


            if (emailentered.isEmpty() || passwordentered.isEmpty()) {
                //hiding progress bar
                progressDialog.hide()
                Toast.makeText(this, "Please enter your email and password", Toast.LENGTH_LONG).show()

            } else {

                //creating authentication on firebase with email and password recieved
                mAuth!!.createUserWithEmailAndPassword(emailentered, passwordentered)
                        .addOnCompleteListener(this) { task ->

                            if (task.isSuccessful) {

                                //call the current registeres user
                                val currentUser = mAuth!!.currentUser

                                //getting the unique user id which is to be used for saving data to firestore as document
                                val userAuthID = currentUser!!.uid


                                //saving user infromation to firestore
                                val users = HashMap<String, Any>()
                                users.put("Fullname", regfullnamereg.text.toString().trim())
                                users.put("Email", regEmailreg.text.toString().trim())
                                users.put("Paswword", regPassWord.text.toString().trim())
                                users.put("regnum", regRegNum.text.toString().trim())

                                db.collection("RegisteredUsers").document(userAuthID).set(users)
                                        .addOnSuccessListener {
                                            Toast.makeText(this, "Information Saved to Database", Toast.LENGTH_LONG).show()
                                        }
                                        .addOnFailureListener {
                                            Toast.makeText(this, "upss....Information not Saved to Database", Toast.LENGTH_LONG).show()
                                        }


                                //hiding progress bar
                                progressDialog.hide()

                                //display this toast if the registration is succesful
                                Toast.makeText(applicationContext, "Succesfull Registration", Toast.LENGTH_LONG).show()
                                val intentToStudentPage = Intent(this, StudentPage::class.java)
                                startActivity(intentToStudentPage)

                            } else {
                                //hiding progress bar
                                progressDialog.hide()
                                //display this toast if the registration is succesful
                                Toast.makeText(applicationContext, "Registration Unsuccesfull... Please Try again", Toast.LENGTH_LONG).show()
                            }
                        }
            }
        }

    }

}
