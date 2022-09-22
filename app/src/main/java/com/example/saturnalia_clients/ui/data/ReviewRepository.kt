package com.example.saturnalia_clients.ui.data

import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class ReviewRepository {

    private var auth: FirebaseAuth = Firebase.auth
    private var db = Firebase.firestore

    suspend fun loadReviews(): ResourceRemote<QuerySnapshot>{
        return try{
            val docRef = auth.uid?.let { db.collection("discos").document(it).collection("Reviews") }
            val result = docRef?.get()?.await()
            ResourceRemote.success(data = result)
        } catch (e: FirebaseFirestoreException){
            ResourceRemote.error(message = e.localizedMessage)
        } catch (e: FirebaseNetworkException){
            ResourceRemote.error(message = e.localizedMessage)
        }
    }
}