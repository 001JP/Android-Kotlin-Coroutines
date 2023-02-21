package com.example.coroutines

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private var text : TextView? = null
    private var progressDialog : Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button : Button = findViewById(R.id.button)

        text = findViewById<TextView>(R.id.text_view)

        text?.text = "App Running"

        button.setOnClickListener{

            progressDialog()
            lifecycleScope.launch{
             execute("Task Done")
            }

        }

    }

    private suspend fun execute(result:String){
        withContext(Dispatchers.IO){

            for(i in 1..10000){
                Log.e("delay: ", "" + i)

                runOnUiThread{
                    text?.text = i.toString()
                }
            }

            runOnUiThread {
                progressDialog?.dismiss()
                Toast.makeText(this@MainActivity, "$result", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun progressDialog(){
        progressDialog = Dialog(this@MainActivity)
        progressDialog?.setContentView(R.layout.progress_dialog)
        progressDialog?.show()
    }


}



