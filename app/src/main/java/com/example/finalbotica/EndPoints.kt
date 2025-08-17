package com.example.finalbotica


object EndPoints {
    private val URL_ROOT = "http://192.168.56.1/webapi/v1/?op="
    val URL_ADD_MEDICAMENTO = URL_ROOT + "createMedicamento"
    val URL_GET_MEDICAMENTOS = URL_ROOT + "getMedicamentos"
    val URL_DELETE_MEDICAMENTO = URL_ROOT + "deleteMedicamento"
}