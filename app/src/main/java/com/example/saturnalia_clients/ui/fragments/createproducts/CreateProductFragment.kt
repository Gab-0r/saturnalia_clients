package com.example.saturnalia_clients.ui.fragments.createproducts

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.saturnalia_clients.R
import com.example.saturnalia_clients.databinding.FragmentCreateProductBinding

class CreateProductFragment : Fragment() {

    private lateinit var createProductBinding: FragmentCreateProductBinding
    private lateinit var createProductViewModel: CreateProductViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        createProductBinding = FragmentCreateProductBinding.inflate(inflater, container, false)
        createProductViewModel = ViewModelProvider(this)[CreateProductViewModel::class.java]

        createProductViewModel.msg.observe(viewLifecycleOwner){
            showMsg(it)
        }

        createProductViewModel.createProductSuccess.observe(viewLifecycleOwner){
            goToCarta()
        }

        with(createProductBinding){
            buttonEditProduct.setOnClickListener {
                createProductViewModel.checkFields(editTextCreateProductName.text.toString(),
                    editTextProductType.text.toString(), editTextProductPrice.text.toString().toInt(),
                    editTextDescProduct.text.toString(), checkBoxAlcohol.isChecked
                )
            }
        }

        return createProductBinding.root
    }

    private fun goToCarta() {
        findNavController().navigate(CreateProductFragmentDirections.actionNavigationCreateProductToNavigationCarta())
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.hide()
    }

    fun showMsg(msg_: String?){
        Toast.makeText(requireActivity(), msg_, Toast.LENGTH_LONG).show()
    }
}