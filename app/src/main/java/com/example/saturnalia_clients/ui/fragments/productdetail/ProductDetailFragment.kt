package com.example.saturnalia_clients.ui.fragments.productdetail

import android.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.saturnalia_clients.R
import com.example.saturnalia_clients.databinding.FragmentProductDetailBinding
import com.example.saturnalia_clients.ui.model.Product
import com.squareup.picasso.Picasso

class ProductDetailFragment : Fragment() {

    private lateinit var productDetailBinding: FragmentProductDetailBinding
    private lateinit var productDetailViewModel: ProductDetailViewModel

    private val args: ProductDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        productDetailBinding = FragmentProductDetailBinding.inflate(inflater, container, false)
        productDetailViewModel = ViewModelProvider(this)[ProductDetailViewModel::class.java]

        val product = args.product

       productDetailViewModel.deleteProductSuccess.observe(viewLifecycleOwner){
           goToProducts()
       }


        with(productDetailBinding){
            productNameDetail.text = product.productName
            productTypeDetail.text = product.productType
            productPriceDetail.text = product.productPrice.toString()
            productDescDetail.text = product.productDescription
            checkBoxAlcoholDetail.isChecked = product.containsAlcohol!!
            if(product.urlPhoto != null)
                Picasso.get().load(product.urlPhoto).into(productImgDetail)

            editProductButton.setOnClickListener {
                goToEditProduct(product)
            }
            deleteProductButton.setOnClickListener { deleteProduct(product) }

        }

        return productDetailBinding.root
    }

    private fun goToProducts() {
        findNavController().navigate(ProductDetailFragmentDirections.actionNavigationProductDetailToNavigationCarta())
    }

    private fun deleteProduct(product: Product) {
        val alertDialog: AlertDialog? = activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setMessage(String.format("¿Desea eliminar al evento %s ?", product.productName))
                setPositiveButton(R.string.accept){dialog, id->
                    productDetailViewModel.deleteItem(product)
                }
                setNegativeButton(R.string.cancel){dialog, id->
                }
            }
            builder.create()
        }
        alertDialog?.show()
    }

    private fun goToEditProduct(product: Product) {
        findNavController().navigate(ProductDetailFragmentDirections.actionNavigationProductDetailToNavigationEditProducts(product))
    }
}