package com.datute.baitap10

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, "todo.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE users (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "username TEXT," +
                    "password TEXT)"
        )

        db.execSQL(
            "CREATE TABLE tasks (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "user_id INTEGER," +
                    "title TEXT)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS users")
        db.execSQL("DROP TABLE IF EXISTS tasks")
        onCreate(db)
    }

    fun register(username: String, password: String): Boolean {
        val db = writableDatabase
        val cv = ContentValues()
        cv.put("username", username)
        cv.put("password", password)
        return db.insert("users", null, cv) != -1L
    }

    fun login(username: String, password: String): Int {
        val db = readableDatabase
        val c = db.rawQuery(
            "SELECT id FROM users WHERE username=? AND password=?",
            arrayOf(username, password)
        )
        return if (c.moveToFirst()) c.getInt(0) else -1
    }

    fun addTask(userId: Int, title: String) {
        val db = writableDatabase
        val cv = ContentValues()
        cv.put("user_id", userId)
        cv.put("title", title)
        db.insert("tasks", null, cv)
    }

    fun getTasks(userId: Int): ArrayList<Task> {
        val list = ArrayList<Task>()
        val db = readableDatabase
        val c = db.rawQuery(
            "SELECT id, title FROM tasks WHERE user_id=?",
            arrayOf(userId.toString())
        )
        while (c.moveToNext()) {
            list.add(Task(c.getInt(0), c.getString(1)))
        }
        c.close()
        return list
    }

    fun updateTask(id: Int, title: String) {
        val db = writableDatabase
        val cv = ContentValues()
        cv.put("title", title)
        db.update("tasks", cv, "id=?", arrayOf(id.toString()))
    }

    fun deleteTask(id: Int) {
        val db = writableDatabase
        db.delete("tasks", "id=?", arrayOf(id.toString()))
    }
}