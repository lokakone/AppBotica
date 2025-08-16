package com.example.finalbotica

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

class ViewMedicamentosActivity : AppCompatActivity() {

    private var listView: ListView? = null
    private var medicamentList: MutableList<Medicamentos>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.viewmedicamentos)

        listView = findViewById(R.id.listViewMedicamentos) as ListView
        medicamentList = mutableListOf<Medicamentos>()
        loadArtists()
    }

    private fun loadArtists() {
        val stringRequest = StringRequest(
            Request.Method.GET,
            EndPoints.URL_GET_ARTIST,
            Response.Listener<String> { s ->
                try {
                    val obj = JSONObject(s)
                    if (!obj.getBoolean("error")) {
                        val array = obj.getJSONArray("artists")  // 👈 aquí el cambio

                        medicamentList!!.clear() // limpia la lista antes de volver a llenarla

                        for (i in 0 until array.length()) {
                            val objectArtist = array.getJSONObject(i)
                            val artist = Medicamentos(
                                objectArtist.getString("name"),
                                objectArtist.getString("genre")
                            )
                            medicamentList!!.add(artist)
                        }

                        // 👉 Solo una vez, fuera del for
                        val adapter = MedicamentosList(this@ViewMedicamentosActivity, medicamentList!!)
                        listView!!.adapter = adapter

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