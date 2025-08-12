package com.example.finalbotica

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    lateinit var btncontinuar: Button
    lateinit var btncerrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btncontinuar = findViewById(R.id.btncontinuar)
        btncerrar = findViewById(R.id.btncerrar)

        btncontinuar.setOnClickListener {
            val pantalla1 =Intent(this, MainActivity2::class.java)
            startActivity(pantalla1)
        }

        btncerrar.setOnClickListener {
            cerrar()
        }
    }
    fun cerrar(){
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder
            .setMessage("Fin de la APP!!!")
            .setTitle("Cerrar APP")
            .setPositiveButton(android.R.string.yes){ dialog, which -> Toast.makeText(applicationContext, android.R.string.yes, Toast.LENGTH_SHORT)

                System.exit(0)

            }
            .setNegativeButton(android.R.string.no){dialog, wich -> Toast.makeText(applicationContext,android.R.string.no, Toast.LENGTH_SHORT).show()}
        val dialog: AlertDialog = builder.create()
        dialog.show()

    }
}