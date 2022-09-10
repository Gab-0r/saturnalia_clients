package com.example.saturnalia_clients.ui.fragments.carta

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.saturnalia_clients.R
import com.example.saturnalia_clients.databinding.FragmentCartaBinding
import com.example.saturnalia_clients.databinding.FragmentCreateProductBinding

class CartaFragment : Fragment() {

    private lateinit var cartaBinding: FragmentCartaBinding
    private lateinit var cartaViewModel: CartaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        cartaBinding = FragmentCartaBinding.inflate(inflater, container, false)
        cartaViewModel = ViewModelProvider(this)[CartaViewModel::class.java]


        with(cartaBinding){
            buttonToCreateProduct.setOnClickListener{
                goToCreateProduct()
            }
        }

        return cartaBinding.root
    }

    private fun goToCreateProduct(){
        findNavController().navigate(CartaFragmentDirections.actionNavigationCartaToNavigationCreateProduct())
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.hide()
    }
}