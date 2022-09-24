package com.example.saturnalia_clients.ui.fragments.editproducts

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.saturnalia_clients.databinding.FragmentEditProductsBinding

class EditProductsFragment : Fragment() {

    private lateinit var editProductBinding: FragmentEditProductsBinding
    private lateinit var editProductViewModel: EditProductsViewModel

    private val args: EditProductsFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        editProductBinding = FragmentEditProductsBinding.inflate(layoutInflater, container, false)
        editProductViewModel = ViewModelProvider(this)[EditProductsViewModel::class.java]

        val view = editProductBinding.root

        val product = args.productToEdit

        with(editProductBinding){
            editTextEditProductName.setText(product.productName)
            editTextEditProductType.setText(product.productType)
            editTextEditProductPrice.setText(product.productPrice.toString())
            editTextDescProduct.setText(product.productDescription)
        }

        return view
    }
}