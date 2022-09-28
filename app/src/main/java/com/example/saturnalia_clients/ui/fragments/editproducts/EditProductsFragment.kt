package com.example.saturnalia_clients.ui.fragments.editproducts

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.saturnalia_clients.databinding.FragmentEditProductsBinding
import com.squareup.picasso.Picasso

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

        editProductViewModel.editProductSuccess.observe(viewLifecycleOwner){
            goToCarta()
        }

        editProductViewModel.msg.observe(viewLifecycleOwner){
            showMsg(it)
        }

        with(editProductBinding){
            editTextEditProductName.setText(product.productName)
            editTextEditProductType.setText(product.productType)
            editTextEditProductPrice.setText(product.productPrice.toString())
            editTextDescProduct.setText(product.productDescription)
            checkBoxAlcoholEdit.isChecked = product.containsAlcohol!!

            if (product.urlPhoto != null)
                Picasso.get().load(product.urlPhoto).into(imageViewEditProduct)

            buttonEditProduct.setOnClickListener {
                editProductViewModel.checkFields(product.id.toString(), editTextEditProductName.text.toString(),
                editTextEditProductType.text.toString(), editTextEditProductPrice.text.toString(), editTextDescProduct.text.toString(),
                checkBoxAlcoholEdit.isChecked)

            }

        }

        return view
    }

    private fun showMsg(msg: String?) {
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_LONG).show()
    }

    private fun goToCarta() {
        findNavController().navigate(EditProductsFragmentDirections.actionNavigationEditProductsToNavigationCarta())
    }
}