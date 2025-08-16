package com.example.finalbotica


object EndPoints {
    private val URL_ROOT = "http://192.168.56.1/webapi/v1/?op="
    val URL_ADD_ARTIST = URL_ROOT + "addartist"
    val URL_GET_ARTIST = URL_ROOT + "getartists"
    val URL_BORRA_ARTIST = URL_ROOT + "borraartista"
}