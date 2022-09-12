package com.example.saturnalia_clients.ui.fragments.carta

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saturnalia_clients.ui.data.ProductRepository
import com.example.saturnalia_clients.ui.data.ResourceRemote
import com.example.saturnalia_clients.ui.model.Product
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.launch

class CartaViewModel : ViewModel() {

    private val productRepository = ProductRepository()

    private var productList: ArrayList<Product> = ArrayList()

    private val _productsList: MutableLiveData<ArrayList<Product>> = MutableLiveData()
    val productsList: LiveData<ArrayList<Product>> = _productsList

    private val _msg: MutableLiveData<String?> = MutableLiveData()
    val msg: LiveData<String?> = _msg

    fun loadProducts() {
        viewModelScope.launch {
            var result = productRepository.loadProducts()
            result.let { resourceRemote ->
                when(resourceRemote){
                    is ResourceRemote.success -> {
                        result.data?.documents?.forEach{ document->
                            val product = document.toObject<Product>()
                            product?.let { productList.add(product) }
                        }
                        _productsList.postValue(productList)
                    }
                    is ResourceRemote.error -> {
                        val msg = result.message
                        _msg.postValue(msg)
                    }
                    else -> {
                        //don't use
                    }
                }
            }

        }
    }
}