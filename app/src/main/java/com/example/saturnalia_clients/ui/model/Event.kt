package com.example.saturnalia_clients.ui.model

import java.io.Serializable


data class Event(
    var id: String? = null,
    var name: String? = null,
    var description: String? = null,
    var date: String? = null,
    var time: String? = null,
    var cover: String? = null,
    var urlPhoto: String? = null
) : Serializable