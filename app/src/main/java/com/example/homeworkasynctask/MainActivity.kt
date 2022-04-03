package com.example.homeworkasynctask


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText: EditText = findViewById(R.id.edt_apple_count)
        val editText1: EditText = findViewById(R.id.edt_apple_max_count)

        val submitButton: Button = findViewById(R.id.btn_next)

        submitButton.setOnClickListener {
            val enteredData: String = editText.text.toString()
            val enteredData1: String = editText1.text.toString()

            if ((enteredData.isEmpty() || enteredData1.isEmpty()) || (enteredData.toInt() > enteredData1.toInt())) {
                Toast.makeText(applicationContext, "Please Enter right dates", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val intent = Intent(this, MainActivity2::class.java)
                intent.putExtra("appleCount", editText.text.toString().toInt())
                intent.putExtra("appleMaxCount", editText1.text.toString().toInt())
                startActivity(intent)
            }
        }

    }
}