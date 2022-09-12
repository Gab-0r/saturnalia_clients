package com.example.saturnalia_clients.ui.model

import java.io.Serializable

data class Product(
    var id: String?  = null,
    var productName: String? = null,
    var productType: String? = null,
    var productPrice: Int? = null,
    var productDescription: String? = null,
    var urlPhoto: String? = null
) : Serializable
