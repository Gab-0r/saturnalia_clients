package com.example.saturnalia_clients.ui.fragments.carta

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.saturnalia_clients.R
import com.example.saturnalia_clients.databinding.CardViewProductItemBinding
import com.example.saturnalia_clients.ui.model.Product
import com.squareup.picasso.Picasso

class ProductAdapter(
    private val productList : ArrayList<Product>,
    private val onItemClicked : (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder> () {

    //Asignar el XML que se va a pintar
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view_product_item, parent, false)
        return ProductViewHolder(view)
    }

    //Coger un elemento de la lista de productos y lo pinta
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.bind(product)
        holder.itemView.setOnClickListener{onItemClicked(productList[position])}
    }

    override fun getItemCount(): Int = productList.size

    fun appendItems(newList: ArrayList<Product>){
        productList.clear()
        productList.addAll(newList)
        notifyDataSetChanged()
    }

    class ProductViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        private  var binding = CardViewProductItemBinding.bind(itemView)

        fun bind(product_: Product){
            with(binding){
                productNameCard.text = product_.productName
                productPriceCard.text = product_.productPrice.toString()
                //imagen
                if (product_.urlPhoto != null)
                    Picasso.get().load(product_.urlPhoto).into(productCardImage)
            }
        }
    }
}