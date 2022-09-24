package com.example.saturnalia_clients.ui.fragments.productdetail

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

        val product = args.product

        with(productDetailBinding){
            productNameDetail.text = product.productName
            productTypeDetail.text = product.productType
            productPriceDetail.text = product.productPrice.toString()
            productDescDetail.text = product.productDescription
            if(product.urlPhoto != null)
                Picasso.get().load(product.urlPhoto).into(productImgDetail)

            editProductButton.setOnClickListener {
                goToEditProduct()
            }

        }

        return productDetailBinding.root
    }

    private fun goToEditProduct() {
        findNavController().navigate(ProductDetailFragmentDirections.actionNavigationProductDetailToNavigationEditProducts())
    }
}