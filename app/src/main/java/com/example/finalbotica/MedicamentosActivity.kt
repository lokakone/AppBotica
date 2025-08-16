package com.example.finalbotica

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.content.Intent
import android.view.View
import android.widget.*
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import org.json.JSONException
import org.json.JSONObject


class MedicamentosActivity : AppCompatActivity() {

    //edittext and spinner
    private var editTextArtistName: EditText? = null
    private var spinnerGenre: Spinner? = null
    lateinit var btnagregar: Button
    lateinit var btnver: Button
    lateinit var btnborra: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main6)

        //getting it from xml
        editTextArtistName = findViewById(R.id.editTextArtistName) as EditText
        spinnerGenre = findViewById(R.id.spinnerGenre) as Spinner
        btnagregar = findViewById(R.id.buttonAddArtist)
        btnver = findViewById(R.id.buttonViewArtist)
        btnborra = findViewById(R.id.buttonBorraArtist)

        //adding a click listener to button
        btnagregar.setOnClickListener { addArtist() }

        btnborra.setOnClickListener { BorraArtista() }

        //in the second button click
        //opening the activity to display all the artist
        //it will give error as we dont have this activity so remove this part for now to run the app
        btnver.setOnClickListener {
            val intent = Intent(applicationContext, ViewMedicamentosActivity::class.java)
            startActivity(intent)
        }
    }

    //adding a new record to database
    private fun addArtist() {
        //getting the record values
        val name = editTextArtistName?.text.toString()
        val genre = spinnerGenre?.selectedItem.toString()

        //creating volley string request
        val stringRequest = object : StringRequest(Request.Method.POST, EndPoints.URL_ADD_ARTIST,
            Response.Listener<String> { response ->
                try {
                    val obj = JSONObject(response)
                    Toast.makeText(applicationContext, obj.getString("message"), Toast.LENGTH_LONG).show()
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            object : Response.ErrorListener {
                override fun onErrorResponse(volleyError: VolleyError) {
                    Toast.makeText(applicationContext, volleyError.message, Toast.LENGTH_LONG).show()
                }
            }) {
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params.put("nombre", name)
                params.put("genero", genre)
                return params
            }
        }

        //adding request to queue
        VolleySingleton.instance?.addToRequestQueue(stringRequest)
    }

    private fun BorraArtista(){
        val nombre = editTextArtistName?.text.toString()

        if(!nombre.isEmpty()){

            val stringRequest = object : StringRequest(Request.Method.POST, EndPoints.URL_BORRA_ARTIST,
                Response.Listener<String> { response ->
                    try {
                        val obj = JSONObject(response)
                        Toast.makeText(applicationContext, obj.getString("message"), Toast.LENGTH_LONG).show()
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                },
                object : Response.ErrorListener {
                    override fun onErrorResponse(volleyError: VolleyError) {
                        Toast.makeText(applicationContext, volleyError.message, Toast.LENGTH_LONG).show()
                    }
                }) {
                @Throws(AuthFailureError::class)
                override fun getParams(): Map<String, String> {
                    val params = HashMap<String, String>()
                    params.put("nombre", nombre)
                    return params
                }
            }

            //adding request to queue
            VolleySingleton.instance?.addToRequestQueue(stringRequest)
        }else{
            Toast.makeText(this, "Ingrese el artista a borrar!!", Toast.LENGTH_SHORT).show()
        }

    }
}