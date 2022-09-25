package com.example.saturnalia_clients.ui.fragments.productdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saturnalia_clients.ui.data.ProductRepository
import com.example.saturnalia_clients.ui.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class ProductDetailViewModel : ViewModel() {

    private val productRepository = ProductRepository()

    private val _deleteProductSuccess: MutableLiveData<String?> = MutableLiveData()
    val deleteProductSuccess: LiveData<String?> = _deleteProductSuccess

    fun deleteItem(product: Product) {

        viewModelScope.launch(Dispatchers.IO){
            productRepository.deleteProduct(product)
        }
        _deleteProductSuccess.value = "Producto eliminado"
    }
}