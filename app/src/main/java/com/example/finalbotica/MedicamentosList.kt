package com.example.finalbotica

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class MedicamentosList(private val context: Activity, internal var medicaments: List<Medicamentos>) : ArrayAdapter<Medicamentos>(context, R.layout.layout_list_medicamentos, medicaments) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val listViewItem = inflater.inflate(R.layout.layout_list_medicamentos, null, true)

        val textViewName = listViewItem.findViewById(R.id.textViewName) as TextView
        val textViewGenre = listViewItem.findViewById(R.id.textViewGenre) as TextView

        val medi = medicaments[position]
        textViewName.text = medi.name
        textViewGenre.text = medi.genre

        return listViewItem
    }
}