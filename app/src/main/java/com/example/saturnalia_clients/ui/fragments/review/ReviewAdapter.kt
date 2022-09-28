package com.example.saturnalia_clients.ui.fragments.review

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.saturnalia_clients.R
import com.example.saturnalia_clients.databinding.CardViewReviewItemBinding
import com.example.saturnalia_clients.ui.fragments.eventos.EventAdapter
import com.example.saturnalia_clients.ui.model.Review

class ReviewAdapter (
    private val reviewList: ArrayList<Review>,
) : RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> (){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view_review_item, parent, false)
        return ReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val review = reviewList[position]
        holder.bind(review)
    }

    override fun getItemCount(): Int = reviewList.size

    fun appendItems(newList: ArrayList<Review>){
        reviewList.clear()
        reviewList.addAll(newList)
        notifyDataSetChanged()
    }

    class ReviewViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var binding = CardViewReviewItemBinding.bind(itemView)

        fun bind(review_ : Review){
            with(binding){
                textViewAuthName.text = review_.authName
                ratingBarReview.rating = review_.score.toString().toFloat()
                textViewReviewText.text = review_.desc
            }
        }
    }
}