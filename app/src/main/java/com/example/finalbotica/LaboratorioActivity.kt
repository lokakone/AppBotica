package com.example.finalbotica

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import org.json.JSONException
import org.json.JSONObject

class LaboratorioActivity : AppCompatActivity() {

    private lateinit var txtRuc: EditText
    private lateinit var txtRazonsocial: EditText
    private lateinit var txtDireccion: EditText
    private lateinit var txtMovil: EditText
    private lateinit var txtContacto: EditText
    private lateinit var txtEmail: EditText

    private lateinit var btnAgregar: Button
    private lateinit var btnVer: Button
    private lateinit var btnBorrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_laboratorio)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true) // Activa el botón "atrás" (home)
            setHomeAsUpIndicator(R.drawable.ic_baseline_reply_24) // Cambia icono
        }
        txtRuc = findViewById(R.id.txtruc)
        txtRazonsocial = findViewById(R.id.txtrazonsocial)
        txtDireccion = findViewById(R.id.txtdireccion)
        txtMovil = findViewById(R.id.txtmovil)
        txtContacto = findViewById(R.id.txtcontacto)
        txtEmail = findViewById(R.id.txtemail)

        btnAgregar = findViewById(R.id.buttonAddLaboratorio)
        btnVer = findViewById(R.id.buttonViewLaboratorio)
        btnBorrar = findViewById(R.id.buttonBorraLaboratorio)

        btnAgregar.setOnClickListener { addLaboratorio() }
        btnBorrar.setOnClickListener { borrarLaboratorio() }

        btnVer.setOnClickListener {
            val intent = Intent(applicationContext, ViewLaboratorioActivity::class.java)
            startActivity(intent)
        }

    }
    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> { // Este es el botón "up" (flecha o icono que pusiste)

                finish() // opcional, para que no vuelva aquí al presionar atrás
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    // Insertar laboratorio
    private fun addLaboratorio() {
        val stringRequest = object : StringRequest(
            Request.Method.POST, EndPoints.URL_ADD_LABORATORIO,
            Response.Listener<String> { response ->
                try {
                    val obj = JSONObject(response)
                    Toast.makeText(applicationContext, obj.getString("message"), Toast.LENGTH_LONG).show()
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            Response.ErrorListener { volleyError ->
                Toast.makeText(applicationContext, volleyError.message, Toast.LENGTH_LONG).show()
            }) {

            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params["ruc_laboratorio"] = txtRuc.text.toString()
                params["razon_social"] = txtRazonsocial.text.toString()
                params["direccion"] = txtDireccion.text.toString()
                params["movil"] = txtMovil.text.toString()
                params["contacto"] = txtContacto.text.toString()
                params["email"] = txtEmail.text.toString()
                return params
            }
        }
        VolleySingleton.instance?.addToRequestQueue(stringRequest)
    }
    // Borrar Laboratorio
    private fun borrarLaboratorio() {
        val id = txtRuc.text.toString()
        if (id.isNotEmpty()) {
            val stringRequest = object : StringRequest(
                Request.Method.POST, EndPoints.URL_DELETE_LABORATORIO,
                Response.Listener<String> { response ->
                    try {
                        val obj = JSONObject(response)
                        Toast.makeText(applicationContext, obj.getString("message"), Toast.LENGTH_LONG).show()
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                },
                Response.ErrorListener { volleyError ->
                    Toast.makeText(applicationContext, volleyError.message, Toast.LENGTH_LONG).show()
                }) {
                @Throws(AuthFailureError::class)
                override fun getParams(): Map<String, String> {
                    val params = HashMap<String, String>()
                    params["ruc_laboratorio"] = id
                    return params
                }
            }
            VolleySingleton.instance?.addToRequestQueue(stringRequest)
        } else {
            Toast.makeText(this, "Ingrese el RUC del laboratorio a borrar!!", Toast.LENGTH_SHORT).show()
        }
    }
}