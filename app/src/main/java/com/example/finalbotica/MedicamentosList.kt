package com.example.finalbotica

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class MedicamentosList(
    private val context: Activity,
    internal var medicaments: List<Medicamentos>
) : ArrayAdapter<Medicamentos>(context, R.layout.layout_list_medicamentos, medicaments) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val listViewItem = inflater.inflate(R.layout.layout_list_medicamentos, null, true)

        val tvId = listViewItem.findViewById<TextView>(R.id.textViewId)
        val tvDescripcion = listViewItem.findViewById<TextView>(R.id.textViewDescripcion)
        val tvPresentacion = listViewItem.findViewById<TextView>(R.id.textViewPresentacion)
        val tvInventario = listViewItem.findViewById<TextView>(R.id.textViewInventario)
        val tvStock = listViewItem.findViewById<TextView>(R.id.textViewStock)
        val tvPrecioCosto = listViewItem.findViewById<TextView>(R.id.textViewPrecioCosto)
        val tvPrecioVenta = listViewItem.findViewById<TextView>(R.id.textViewPrecioVenta)
        val tvObservacion = listViewItem.findViewById<TextView>(R.id.textViewObservacion)

        val medi = medicaments[position]

        tvId.text = "ID: ${medi.idmedicamento}"
        tvDescripcion.text = "Descripción: ${medi.descripcion}"
        tvPresentacion.text = "Presentación: ${medi.presentacion}"
        tvInventario.text = "Inventario: ${medi.inventario}"
        tvStock.text = "Stock: ${medi.stock_disponible}"
        tvPrecioCosto.text = "Precio Costo: S/ ${medi.precio_costo}"
        tvPrecioVenta.text = "Precio Venta: S/ ${medi.precio_venta}"
        tvObservacion.text = "Observación: ${medi.observacion}"

        return listViewItem
    }
}
