package com.datute.baitap10

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val db = DatabaseHelper(this)

        val user = findViewById<EditText>(R.id.edtUser)
        val pass = findViewById<EditText>(R.id.edtPass)

        findViewById<Button>(R.id.btnCreate).setOnClickListener {
            if (db.register(user.text.toString(), pass.text.toString())) {
                Toast.makeText(this, "Register success", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Register failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}