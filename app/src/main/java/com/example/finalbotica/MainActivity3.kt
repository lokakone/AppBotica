package com.example.finalbotica

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import android.widget.SearchView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject
import android.widget.ArrayAdapter

class MainActivity3 : AppCompatActivity() {

    private lateinit var listamedi: ListView
    private lateinit var searchView: SearchView
    private lateinit var medicamentList: MutableList<Medicamentos>
    private lateinit var adapter: MedicamentosList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        listamedi = findViewById(R.id.listamedi)
        searchView = findViewById(R.id.searchView)
        medicamentList = mutableListOf()

        // cargamos los medicamentos desde la BD
        loadMedicamentos()

        // filtrado con SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false

            override fun onQueryTextChange(newText: String?): Boolean {
                val filtered = medicamentList.filter {
                    it.descripcion.contains(newText ?: "", ignoreCase = true)
                }
                adapter = MedicamentosList(this@MainActivity3, filtered.toMutableList())
                listamedi.adapter = adapter
                return true
            }
        })
    }

    private fun loadMedicamentos() {
        val stringRequest = StringRequest(
            Request.Method.GET,
            EndPoints.URL_GET_MEDICAMENTOS,
            Response.Listener { response ->
                try {
                    val obj = JSONObject(response)
                    if (!obj.getBoolean("error")) {
                        val array = obj.getJSONArray("medicamentos")
                        medicamentList.clear()

                        for (i in 0 until array.length()) {
                            val objectMed = array.getJSONObject(i)
                            val medicamento = Medicamentos(
                                objectMed.getString("idmedicamento"),
                                objectMed.getString("descripcion"),
                                objectMed.getString("presentacion"),
                                objectMed.getInt("inventario"),
                                objectMed.getInt("stock_disponible"),
                                objectMed.getDouble("precio_costo"),
                                objectMed.getDouble("precio_venta"),
                                objectMed.getString("observacion")
                            )
                            medicamentList.add(medicamento)
                        }

                        val listaResumida = medicamentList.map { med ->
                            "📌 ${med.descripcion}\n" +
                                    "💊 ${med.presentacion}\n" +
                                    "📦 Stock: ${med.stock_disponible}\n" +
                                    "💲 Precio: S/ ${med.precio_venta}\n" +
                                    "📝 ${med.observacion}"
                        }


                        val adapter = ArrayAdapter(
                            this@MainActivity3,
                            android.R.layout.simple_list_item_1,
                            listaResumida
                        )
                        listamedi.adapter = adapter

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
    }

    fun ubicacion(){
        val pantalla1 = Intent(this, MapsActivity::class.java)
        startActivity(pantalla1)
    }

    fun misionvision(){
        val pantalla1 = Intent(this, MainActivity5::class.java)
        startActivity(pantalla1)
    }

    fun medicamentos(){
        val pantalla1 = Intent(this, MedicamentosActivity::class.java)
        startActivity(pantalla1)
    }

    fun laboratorio(){
        val pantalla1 = Intent(this, LaboratorioActivity::class.java)
        startActivity(pantalla1)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.ubicacion -> ubicacion()
            R.id.qSomos -> quienes()
            R.id.misionvision -> misionvision()
            R.id.atras -> atras()
            R.id.medicamento -> medicamentos()
            R.id.laboratorio -> laboratorio()
        }
        return super.onOptionsItemSelected(item)
    }
}

