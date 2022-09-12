package com.example.saturnalia_clients.ui.fragments.carta

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.saturnalia_clients.R
import com.example.saturnalia_clients.databinding.FragmentCartaBinding
import com.example.saturnalia_clients.databinding.FragmentCreateProductBinding
import com.example.saturnalia_clients.ui.model.Product

class CartaFragment : Fragment() {

    private lateinit var cartaBinding: FragmentCartaBinding
    private lateinit var cartaViewModel: CartaViewModel
    private lateinit var productAdapter: ProductAdapter
    private var productList: ArrayList<Product> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        cartaBinding = FragmentCartaBinding.inflate(inflater, container, false)
        cartaViewModel = ViewModelProvider(this)[CartaViewModel::class.java]
        val view = cartaBinding.root

        cartaViewModel.loadProducts()

        cartaViewModel.msg.observe(viewLifecycleOwner){
            showMsg(it)
        }

        cartaViewModel.productsList.observe(viewLifecycleOwner){ list ->
            productAdapter.appendItems(list)
        }

        productAdapter = ProductAdapter(productList, onItemClicked = {onProducItemClicked(it)})

        cartaBinding.productsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@CartaFragment.requireContext())
            adapter = productAdapter
            setHasFixedSize(false)
        }

        with(cartaBinding){
            buttonToCreateProduct.setOnClickListener{
                goToCreateProduct()
            }
        }

        return view
    }

    private fun onProducItemClicked(product_: Product) {
    }

    private fun goToCreateProduct(){
        findNavController().navigate(CartaFragmentDirections.actionNavigationCartaToNavigationCreateProduct())
    }

    private fun showMsg(msg_: String?) {
        Toast.makeText(requireActivity(), msg_, Toast.LENGTH_LONG).show()
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.hide()
    }
}