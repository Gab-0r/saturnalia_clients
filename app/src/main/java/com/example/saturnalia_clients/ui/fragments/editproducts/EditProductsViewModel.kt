package com.example.saturnalia_clients.ui.fragments.editproducts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saturnalia_clients.ui.data.ProductRepository
import com.example.saturnalia_clients.ui.data.ResourceRemote
import com.example.saturnalia_clients.ui.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditProductsViewModel : ViewModel() {

    private val productRepository = ProductRepository()

    private val _msg: MutableLiveData<String> = MutableLiveData()
    val msg: LiveData<String> = _msg

    private val _editProductSuccess: MutableLiveData<String?> = MutableLiveData()
    val editProductSuccess: LiveData<String?> = _editProductSuccess

    fun checkFields(id_: String, name_: String, type_: String, price_: String, desc_: String, alcohol_: Boolean) {
        if(name_.isEmpty() || type_.isEmpty() || price_.isEmpty() || desc_.isEmpty())
            _msg.value = "Todos los campos deben ser llenados"
        else
            viewModelScope.launch(Dispatchers.IO){
                val product = Product(id = id_, productName = name_, productType = type_, productPrice = price_.toInt(), productDescription = desc_, containsAlcohol = alcohol_)
                val result = productRepository.editProduct(product)
                result.let { resourceRemote ->
                    when(resourceRemote){
                        is ResourceRemote.success -> {
                            _editProductSuccess.postValue(result.data)
                            _msg.postValue("Cambios guardados")
                        }
                        is ResourceRemote.error ->{
                            //TODO Mensajes de error con su traducci'on
                        }
                        else ->{
                            //Don't use
                        }
                    }
                }
            }
    }
}