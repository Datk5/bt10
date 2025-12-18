package com.datute.baitap10

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var userId: Int = -1
    private lateinit var db: DatabaseHelper
    private lateinit var listView: ListView
    private lateinit var adapter: TaskAdapter
    private var tasks = ArrayList<Task>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userId = intent.getIntExtra("USER_ID", -1)
        db = DatabaseHelper(this)

        listView = findViewById(R.id.listTask)

        findViewById<Button>(R.id.btnAdd).setOnClickListener {
            val i = Intent(this, AddTaskActivity::class.java)
            i.putExtra("USER_ID", userId)
            startActivity(i)
        }

        adapter = TaskAdapter(this, tasks,
            onEdit = { task -> showEditTaskDialog(task) },
            onDelete = { task ->
                db.deleteTask(task.id)
                updateTasks()
            }
        )
        listView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        updateTasks()
    }

    private fun updateTasks() {
        tasks.clear()
        tasks.addAll(db.getTasks(userId))
        adapter.notifyDataSetChanged()
    }

    private fun showEditTaskDialog(task: Task) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Edit Task")

        val input = EditText(this)
        input.setText(task.title)
        builder.setView(input)

        builder.setPositiveButton("Save") { _, _ ->
            val newTitle = input.text.toString()
            if (newTitle.isNotEmpty()) {
                db.updateTask(task.id, newTitle)
                updateTasks()
            }
        }
        builder.setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }

        builder.show()
    }
}