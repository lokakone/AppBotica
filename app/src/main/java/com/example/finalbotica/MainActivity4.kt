package com.example.finalbotica

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

class MainActivity4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true) // Activa el botón "atrás" (home)
            setHomeAsUpIndicator(R.drawable.ic_baseline_reply_24) // Cambia icono
        }
    }
    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> { // Este es el botón "up" (flecha o icono que pusiste)

                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}