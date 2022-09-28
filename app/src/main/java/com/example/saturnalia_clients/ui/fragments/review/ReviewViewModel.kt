package com.example.saturnalia_clients.ui.fragments.review

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saturnalia_clients.ui.data.ResourceRemote
import com.example.saturnalia_clients.ui.data.ReviewRepository
import com.example.saturnalia_clients.ui.model.Review
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.launch

class ReviewViewModel : ViewModel() {

    private var reviewRepository = ReviewRepository()
    private var reviewList: ArrayList<Review> = ArrayList()
    private var ratingList: ArrayList<Float> = ArrayList()

    private val _reviewsList: MutableLiveData<ArrayList<Review>> = MutableLiveData()
    val reviewsList: LiveData<ArrayList<Review>> = _reviewsList

    private val _averageRating: MutableLiveData<Float> = MutableLiveData()
    val averageRating: LiveData<Float> = _averageRating

    private val _msg: MutableLiveData<String?> = MutableLiveData()
    val msg: LiveData<String?> = _msg

    fun loadReviews() {
        viewModelScope.launch {
            reviewList.clear()
            var result = reviewRepository.loadReviews()
            result.let { resourceRemote ->
                when(resourceRemote){
                    is ResourceRemote.success -> {
                        result?.data?.documents?.forEach { document ->
                            val review = document.toObject<Review>()
                            review?.let { reviewList.add(review) }
                            review?.let { ratingList.add(review.score.toString().toFloat()) }
                        }
                        _reviewsList.postValue(reviewList)
                        var sum = 0f
                        for (i in ratingList.indices){
                            sum += ratingList.get(i)
                        }
                        _averageRating.postValue(sum/ratingList.size)
                    }
                    is ResourceRemote.error -> {
                        val msg = result.message
                        _msg.postValue(msg)
                    }
                    else -> {
                        //don't use
                    }
                }
            }
        }
    }
}