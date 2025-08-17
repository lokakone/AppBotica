package com.example.finalbotica

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.content.Intent
import android.widget.*
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import org.json.JSONException
import org.json.JSONObject


class MedicamentosActivity : AppCompatActivity() {

    private lateinit var txtIdMedicamento: EditText
    private lateinit var txtDescripcion: EditText
    private lateinit var txtPresentacion: EditText
    private lateinit var txtInventario: EditText
    private lateinit var txtStock: EditText
    private lateinit var txtCosto: EditText
    private lateinit var txtVenta: EditText
    private lateinit var txtObservacion: EditText

    private lateinit var btnAgregar: Button
    private lateinit var btnVer: Button
    private lateinit var btnBorrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main6)

        // Vincular con el XML
        txtIdMedicamento = findViewById(R.id.txtidmedicamento)
        txtDescripcion = findViewById(R.id.txtdescripcion)
        txtPresentacion = findViewById(R.id.txtpresentacion)
        txtInventario = findViewById(R.id.txtinventario)
        txtStock = findViewById(R.id.txtstock)
        txtCosto = findViewById(R.id.txtcosto)
        txtVenta = findViewById(R.id.txtventa)
        txtObservacion = findViewById(R.id.txtobservacion)

        btnAgregar = findViewById(R.id.buttonAddMedicamento)
        btnVer = findViewById(R.id.buttonViewMedicamento)
        btnBorrar = findViewById(R.id.buttonBorraMedicamento)

        btnAgregar.setOnClickListener { addMedicamento() }
        btnBorrar.setOnClickListener { borrarMedicamento() }

        btnVer.setOnClickListener {
            val intent = Intent(applicationContext, ViewMedicamentosActivity::class.java)
            startActivity(intent)
        }
    }

    // Insertar medicamento
    private fun addMedicamento() {
        val stringRequest = object : StringRequest(
            Request.Method.POST, EndPoints.URL_ADD_MEDICAMENTO,
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
                params["idmedicamento"] = txtIdMedicamento.text.toString()
                params["descripcion"] = txtDescripcion.text.toString()
                params["presentacion"] = txtPresentacion.text.toString()
                params["inventario"] = txtInventario.text.toString()
                params["stock_disponible"] = txtStock.text.toString()
                params["precio_costo"] = txtCosto.text.toString()
                params["precio_venta"] = txtVenta.text.toString()
                params["observacion"] = txtObservacion.text.toString()
                return params
            }
        }
        VolleySingleton.instance?.addToRequestQueue(stringRequest)
    }

    // Borrar medicamento
    private fun borrarMedicamento() {
        val id = txtIdMedicamento.text.toString()
        if (id.isNotEmpty()) {
            val stringRequest = object : StringRequest(
                Request.Method.POST, EndPoints.URL_DELETE_MEDICAMENTO,
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
                    params["idmedicamento"] = id
                    return params
                }
            }
            VolleySingleton.instance?.addToRequestQueue(stringRequest)
        } else {
            Toast.makeText(this, "Ingrese el ID del medicamento a borrar!!", Toast.LENGTH_SHORT).show()
        }
    }
}
