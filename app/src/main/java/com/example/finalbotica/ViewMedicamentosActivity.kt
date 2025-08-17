package com.example.finalbotica

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

class ViewMedicamentosActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var medicamentList: MutableList<Medicamentos>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.viewmedicamentos)

        listView = findViewById(R.id.listViewMedicamentos)
        medicamentList = mutableListOf()
        loadMedicamentos()
    }

    private fun loadMedicamentos() {
        val stringRequest = StringRequest(
            Request.Method.GET,
            EndPoints.URL_GET_MEDICAMENTOS,
            Response.Listener { response ->
                try {
                    val obj = JSONObject(response)
                    if (!obj.getBoolean("error")) {

                        val array = obj.getJSONArray("medicamentos") // 👈 nombre correcto del JSON

                        medicamentList.clear()

                        for (i in 0 until array.length()) {
                            val objectMed = array.getJSONObject(i)

                            val medicamento = Medicamentos(
                                objectMed.getString("id"),
                                objectMed.getString("descripcion"),
                                objectMed.getString("presentacion"),
                                objectMed.getInt("inventario"),
                                objectMed.getInt("stock"),
                                objectMed.getDouble("precio_costo"),
                                objectMed.getDouble("precio_venta"),
                                objectMed.getString("observacion")
                            )

                            medicamentList.add(medicamento)
                        }

                        val adapter = MedicamentosList(this@ViewMedicamentosActivity, medicamentList)
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
