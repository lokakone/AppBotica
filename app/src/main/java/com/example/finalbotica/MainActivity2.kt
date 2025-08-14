package com.example.finalbotica

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {

    lateinit var txtUsuario: EditText
    lateinit var txtPassword: EditText
    lateinit var btnIniciar: Button
    lateinit var btnCerrar: Button

    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> { // Este es el botón "up" (flecha o icono que pusiste)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish() // opcional, para que no vuelva aquí al presionar atrás
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true) // Activa el botón "atrás" (home)
            setHomeAsUpIndicator(R.drawable.ic_baseline_reply_24) // Cambia icono
        }

        txtUsuario = findViewById(R.id.txtUsuario)
        txtPassword = findViewById(R.id.txtPassword)
        btnIniciar = findViewById(R.id.btnIniciar)
        btnCerrar = findViewById(R.id.btncerrar2)

        btnIniciar.setOnClickListener {
            val usuario = txtUsuario.text.toString().trim()
            val password = txtPassword.text.toString().trim()

            if (usuario.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                if (usuario == "admin" && password == "1234") {
                    val intent = Intent(this, MainActivity3::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Botón cerrar
        btnCerrar.setOnClickListener {
            finishAffinity() // Cierra toda la app
        }
    }
}