package com.example.finalbotica

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ArrayAdapter

class LaboratorioList(
    private val context: Activity,
    private val laboratorioList: List<Laboratorio>
) : ArrayAdapter<Laboratorio>(context, R.layout.list_item_laboratorio, laboratorioList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.list_item_laboratorio, null, true)

        val tvRuc = rowView.findViewById<TextView>(R.id.tvRucLaboratorio)
        val tvRazon = rowView.findViewById<TextView>(R.id.tvRazonSocial)
        val tvDireccion = rowView.findViewById<TextView>(R.id.tvDireccionLaboratorio)
        val tvMovil = rowView.findViewById<TextView>(R.id.tvMovilLaboratorio)
        val tvContacto = rowView.findViewById<TextView>(R.id.tvContactoLaboratorio)
        val tvEmail = rowView.findViewById<TextView>(R.id.tvEmailLaboratorio)

        val laboratorio = laboratorioList[position]

        tvRuc.text = laboratorio.ruc_laboratorio
        tvRazon.text = laboratorio.razon_social
        tvDireccion.text = laboratorio.direccion
        tvMovil.text = laboratorio.movil
        tvContacto.text = laboratorio.contacto
        tvEmail.text = laboratorio.email

        return rowView
    }
}

