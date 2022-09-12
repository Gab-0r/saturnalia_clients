package com.example.saturnalia_clients.ui.fragments.carta

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.saturnalia_clients.R
import com.example.saturnalia_clients.ui.model.Product

class ProductAdapter(
    private val productList : ArrayList<Product>,
    private val onItemClicked : (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder> () {

    //Asignar el XML que se va a pintar
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view_product_item, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    class ProductViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        fun bind(product_: Product){

        }

    }
}