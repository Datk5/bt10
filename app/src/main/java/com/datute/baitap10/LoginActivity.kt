package com.datute.baitap10

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val db = DatabaseHelper(this)

        val user = findViewById<EditText>(R.id.edtUser)
        val pass = findViewById<EditText>(R.id.edtPass)

        findViewById<Button>(R.id.btnLogin).setOnClickListener {
            val id = db.login(user.text.toString(), pass.text.toString())
            if (id != -1) {
                val i = Intent(this, MainActivity::class.java)
                i.putExtra("USER_ID", id)
                startActivity(i)
                finish()
            } else {
                Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show()
            }
        }

        findViewById<Button>(R.id.btnRegister).setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}