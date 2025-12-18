package com.datute.baitap10

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AddTaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val userId = intent.getIntExtra("USER_ID", -1)
        val db = DatabaseHelper(this)

        val edt = findViewById<EditText>(R.id.edtTask)

        findViewById<Button>(R.id.btnSave).setOnClickListener {
            db.addTask(userId, edt.text.toString())
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}