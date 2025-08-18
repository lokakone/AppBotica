package com.example.finalbotica

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

class ViewLaboratorioActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var laboratorioList: MutableList<Laboratorio>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_laboratorio)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true) // Activa el botón "atrás" (home)
            setHomeAsUpIndicator(R.drawable.ic_baseline_reply_24) // Cambia icono
        }

        listView = findViewById(R.id.listViewLaboratorio)
        laboratorioList = mutableListOf()
        loadMedicamentos()
    }
    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {

                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun loadMedicamentos() {
        val stringRequest = StringRequest(
            Request.Method.GET,
            EndPoints.URL_GET_LABORATORIO,
            Response.Listener { response ->
                try {
                    val obj = JSONObject(response)
                    if (!obj.getBoolean("error")) {

                        val array = obj.getJSONArray("laboratorios") // 👈 nombre correcto del JSON

                        laboratorioList.clear()

                        for (i in 0 until array.length()) {
                            val objectMed = array.getJSONObject(i)

                            val laboratorio = Laboratorio(
                                objectMed.getString("ruc_laboratorio"),
                                objectMed.getString("razon_social"),
                                objectMed.getString("direccion"),
                                objectMed.getString("movil"),
                                objectMed.getString("contacto"),
                                objectMed.getString("email")
                            )

                            laboratorioList.add(laboratorio)
                        }

                        val adapter = LaboratorioList(this@ViewLaboratorioActivity, laboratorioList)
                        listView.adapter = adapter

                    } else {
                        Toast.makeText(applicationContext, obj.getString("message"), Toast.LENGTH_LONG).show()
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                    Toast.makeText(applicationContext, "Error parseando JSON", Toast.LENGTH_LONG).show()
                }
            },
            Response.ErrorListener { volleyError ->
                Toast.makeText(applicationContext, volleyError.message, Toast.LENGTH_LONG).show()
            })

        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(stringRequest)
    }
}
