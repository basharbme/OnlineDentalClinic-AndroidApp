package com.healthapps.onlinedentalclinic.utils

class SinglentonOnClick private constructor() {
    var isClick = false
    var callback: (() -> Unit)? = null

    companion object {
        val instance by lazy { SinglentonOnClick() }
    }
}