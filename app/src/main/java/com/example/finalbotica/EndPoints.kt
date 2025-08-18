package com.example.finalbotica

object EndPoints {
    private const val URL_ROOT = "http://192.168.56.1/webapi/v1/?op="

    // Medicamentos
    const val URL_ADD_MEDICAMENTO = URL_ROOT + "createMedicamento"
    const val URL_GET_MEDICAMENTOS = URL_ROOT + "getMedicamentos"
    const val URL_DELETE_MEDICAMENTO = URL_ROOT + "deleteMedicamento"

    // Laboratorios
    const val URL_ADD_LABORATORIO = URL_ROOT + "createLaboratorio"
    const val URL_GET_LABORATORIO = URL_ROOT + "getLaboratorios"
    const val URL_DELETE_LABORATORIO = URL_ROOT + "deleteLaboratorio"

    // Compras
    const val URL_ADD_COMPRA = URL_ROOT + "createCompra"
    const val URL_GET_COMPRAS = URL_ROOT + "getCompras"
    const val URL_DELETE_COMPRA = URL_ROOT + "deleteCompra"
}
