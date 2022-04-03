package com.example.homeworkasynctask

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.coroutines.*
import kotlin.math.max

class MainActivity2 : AppCompatActivity(), View.OnClickListener{
    lateinit var btn_minus: ImageView
    lateinit var btn_plus: ImageView
    lateinit var tv_appleCount: TextView
    lateinit var btn_reset: Button
    var maxCount: Int = 0
    var appleCount: Int = 0
    lateinit var progress: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        tv_appleCount = findViewById(R.id.tv_count)
        btn_minus = findViewById(R.id.imgMinus)
        btn_plus = findViewById(R.id.imgPlus)
        btn_reset = findViewById(R.id.btn_reset)
        progress = findViewById(R.id.progress)
        maxCount = intent.getIntExtra("appleMaxCount", 0)
        appleCount = intent.getIntExtra("appleCount", 0)
        tv_appleCount.text = appleCount.toString()
        Toast.makeText(this, "maxcount = $maxCount , count = $appleCount", Toast.LENGTH_LONG)
            .show()
        btn_minus.setOnClickListener(this)
        btn_plus.setOnClickListener(this)
        btn_reset.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.imgMinus -> {
                    appleCount--

            }
            R.id.imgPlus -> {
                    appleCount++
            }
            R.id.btn_reset -> {
                appleCount = intent.getIntExtra("appleCount", 0)
                btn_reset.visibility = View.INVISIBLE
            }
        }
        GlobalScope.launch(Dispatchers.Default) {
            download()

        }

    }
    suspend fun download(){
        withContext(Dispatchers.Main) {
            btn_minus.isEnabled = false
            btn_plus.isEnabled = false

        }
        for (i in 1..100) {
            delay(10)
            withContext(Dispatchers.Main) {
                progress.progress = i
            }
        }
        withContext(Dispatchers.Main) {
            tv_appleCount.text = appleCount.toString()
            if(appleCount == 0 ){
                btn_plus.isEnabled = true
                btn_reset.visibility = View.VISIBLE
            } else if(appleCount == maxCount){
                btn_minus.isEnabled = true
                btn_reset.visibility = View.VISIBLE
            } else {
                btn_minus.isEnabled = true
                btn_plus.isEnabled = true

            }
        }

    }
}



