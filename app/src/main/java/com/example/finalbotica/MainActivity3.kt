package com.example.finalbotica

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    fun atras() {
        finish()
    }

    fun quienes(){
        val pantalla1 = Intent(this, MainActivity4::class.java)
        startActivity(pantalla1)
        finish()
    }

    fun ubicacion(){
        val pantalla1 = Intent(this, MapsActivity::class.java)
        startActivity(pantalla1)
        finish()
    }

    fun misionvision(){
        val pantalla1 = Intent(this, MainActivity5::class.java)
        startActivity(pantalla1)
        finish()
    }

    fun medicamentos(){
        val pantalla1 = Intent(this, MedicamentosActivity::class.java)
        startActivity(pantalla1)
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.ubicacion -> ubicacion()
            R.id.qSomos -> quienes()
            R.id.misionvision -> misionvision()
            R.id.atras -> atras()
            R.id.medicamento -> medicamentos()
        }
        return super.onOptionsItemSelected(item)
    }

}