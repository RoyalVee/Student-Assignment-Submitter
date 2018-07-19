package com.tech.royal_vee.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_note.*

class AssignmentMessageView : AppCompatActivity() {
    private var firestoreDB: FirebaseFirestore? = null
    internal var id: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assignment_message_view)

        firestoreDB = FirebaseFirestore.getInstance()

        var assginmentviewname = findViewById<TextView>(R.id.AssginmentMessageNameView)
        var assginmentviewregnumber = findViewById<TextView>(R.id.AssignmentMessageRegNumView)
        var assginmentviewmainmessage = findViewById<TextView>(R.id.AssginmentMessageStudentMessage)
        var assginmentviewscoreenter = findViewById<EditText>(R.id.AssginmentMessageScore)

        val bundle = intent.extras
        if (bundle != null) {
            id = bundle.getString("UpdateNoteId")
        }

        firestoreDB!!.collection("RegisteredUsers").document(id).get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val userdetails = task.result
                        var dbName = userdetails.get("Fullname")
                        var message = userdetails.get("Message")
                        var dbRegNumber = userdetails.get("regnum")

                        assginmentviewname.setText(dbName.toString())
                        assginmentviewmainmessage.setText(message.toString())
                        assginmentviewregnumber.setText(dbRegNumber.toString())
                    }
                }

        var assginmentviewsubmit = findViewById<Button>(R.id.AssginmentMessageSubmit)




        assginmentviewsubmit.setOnClickListener {

            val users = HashMap<String, Any>()
            users.put("Score", assginmentviewscoreenter.text.toString())

            firestoreDB!!.collection("RegisteredUsers").document(id).update(users)
                    .addOnSuccessListener {

                        Toast.makeText(this, "Information Update Successful", Toast.LENGTH_LONG).show()
                    }
                    .addOnFailureListener {

                        Toast.makeText(this, "upss....Information not Saved to Database", Toast.LENGTH_LONG).show()
                    }

        }

    }
}
