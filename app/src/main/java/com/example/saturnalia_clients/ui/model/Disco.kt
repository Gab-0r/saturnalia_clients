package com.example.saturnalia_clients.ui.model

import java.io.Serializable

data class Disco(
    var uid: String? = null,
    var name: String? = null,
    var email: String? = null,
    var rating: String? = "0",
    var about: String? = null,
    var address: String? = null,
    var phone: String? = null
) : Serializable