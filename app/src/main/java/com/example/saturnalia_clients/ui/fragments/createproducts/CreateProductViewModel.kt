package com.example.saturnalia_clients.ui.fragments.createproducts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saturnalia_clients.ui.data.ProductRepository
import com.example.saturnalia_clients.ui.data.ResourceRemote
import com.example.saturnalia_clients.ui.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateProductViewModel : ViewModel() {

    private val productRepository = ProductRepository()

    private var _createProductSuccess: MutableLiveData<String?> = MutableLiveData()
    val createProductSuccess: LiveData<String?> = _createProductSuccess

    private val _msg: MutableLiveData<String?> = MutableLiveData()
    val msg: LiveData<String?> = _msg

    fun checkFields(name_: String, type_: String, price_: Int, desc_: String, alcohol_: Boolean) {
        if(name_.isEmpty() || type_.isEmpty() || desc_.isEmpty())
            _msg.value = "Todos los campos deben ser llenados"
        else
            viewModelScope.launch(Dispatchers.IO){
                val product = Product(productName = name_, productType =  type_, productPrice =  price_, productDescription =  desc_, containsAlcohol = alcohol_)
                val result = productRepository.createProduct(product)
                result.let { resourceRemote ->
                    when(resourceRemote){
                        is ResourceRemote.success ->{
                            _createProductSuccess.postValue(result.data)
                            _msg.postValue("Producto creado exitosamente")
                        }
                        is ResourceRemote.error -> {
                            //TODO Mensajes de error con su traduccion
                        }
                        else->{}
                    }
                }
            }
    }
}