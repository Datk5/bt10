package com.datute.baitap10

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView

class TaskAdapter(
    context: Context,
    private val tasks: ArrayList<Task>,
    private val onEdit: (Task) -> Unit,
    private val onDelete: (Task) -> Unit
) : ArrayAdapter<Task>(context, 0, tasks) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.task_item, parent, false)
        val task = getItem(position)!!

        val title = view.findViewById<TextView>(R.id.task_title)
        val editButton = view.findViewById<Button>(R.id.edit_button)
        val deleteButton = view.findViewById<Button>(R.id.delete_button)

        title.text = task.title

        editButton.setOnClickListener {
            onEdit(task)
        }

        deleteButton.setOnClickListener {
            onDelete(task)
        }

        return view
    }
}