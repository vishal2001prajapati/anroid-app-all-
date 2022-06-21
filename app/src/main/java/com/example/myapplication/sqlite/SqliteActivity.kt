package com.example.myapplication.sqlite

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivitySqliteBinding

class SqliteActivity : AppCompatActivity() {

    lateinit var binding: ActivitySqliteBinding
    private val dbConnection: MyDBConnection = MyDBConnection(this, "LoginInfo", 1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySqliteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        insertItems()
        displayItems()
        deleteItem()
        updateItem()

    }

    private fun insertItems() {
        binding.btnInsert.setOnClickListener {
            if (binding.userName.text?.isNotEmpty() == true && binding.userPassword.text?.isNotEmpty() == true) {
                val result = dbConnection.insert(binding.userName.text.toString(), binding.userPassword.text.toString())
                if (result.equals(0)) {
                    Toast.makeText(this, "Record is not Inserted", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Record is Inserted", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please insert values ", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun displayItems() {
        binding.btnDisplay.setOnClickListener {

            val cursor = dbConnection.displayData()
            val data = StringBuffer()
            var count: Int = 0

            if (cursor.count > 0) {
                while (cursor.moveToNext()) {
                    count += 1
                    data.append(count.toString() + ". UserName: " + cursor.getString(1) + ", Password: " + cursor.getString(2) + "\n")
                }
            }
            displayRecords("Records Information", data.toString())
        }
    }

    private fun displayRecords(title: String, data: String) {
        val builder = AlertDialog.Builder(this)
        builder.apply {

            setTitle(title)
            setMessage(data)
            show()
        }
    }

    private fun deleteItem() {
        binding.btnDelete.setOnClickListener {

            if (binding.userName.text?.isNotEmpty() == true) {
                val result = dbConnection.deleteData(binding.userName.text.toString())
                if (result == 0) {
                    Toast.makeText(this, "Record is not Found", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Record deleted", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Enter UserName", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateItem() {

        binding.btnUpdate.setOnClickListener {
            if (binding.userName.text?.isNotEmpty() == true && binding.userPassword.text?.isNotEmpty() == true) {
                val result = dbConnection.update(binding.userName.text.toString(), binding.userPassword.text.toString())
                if (result == 0) {
                    Toast.makeText(this, "Record is not found for  Update", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Record is Update", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please insert values ", Toast.LENGTH_SHORT).show()
            }
        }
    }
}