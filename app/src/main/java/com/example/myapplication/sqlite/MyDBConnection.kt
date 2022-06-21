package com.example.myapplication.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDBConnection(var context: Context, private val dbName: String, var dbVersion: Int) : SQLiteOpenHelper(context, dbName, null, dbVersion) {

    companion object {
        const val tableName: String = "LoginInfo"
        const val columnOne: String = "UserId"
        const val columnTwo: String = "UserName"
        const val columnThree: String = "Password"
    }

    private val createTable: String = "create table $tableName($columnOne integer primary key autoincrement, $columnTwo text, $columnThree text)"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $tableName")
        db?.execSQL(createTable)
    }

    fun insert(userName: String, password: String): Long {
        val db: SQLiteDatabase = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(columnTwo, userName)
            put(columnThree, password)
        }
        return db.insert(tableName, null, contentValues)
    }

    fun displayData(): Cursor {
        val db: SQLiteDatabase = this.readableDatabase
        return db.rawQuery("Select * from $tableName", null)
    }

    fun deleteData(userName: String): Int {
        val db: SQLiteDatabase = this.writableDatabase
        return db.delete(tableName, "$columnTwo=?", arrayOf(userName))
    }

    fun update(userName: String, password: String): Int {
        val db: SQLiteDatabase = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(columnTwo, userName)
            put(columnThree, password)
        }
        return db.update(tableName, contentValues, "$columnTwo=?", arrayOf(userName))
    }

}