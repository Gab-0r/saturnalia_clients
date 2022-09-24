package com.example.saturnalia_clients.ui.data

import android.util.Log
import com.example.saturnalia_clients.ui.model.Product
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class ProductRepository {

    private var auth: FirebaseAuth = Firebase.auth
    private var db = Firebase.firestore

    suspend fun createProduct(product: Product): ResourceRemote<String?> {
        return try {
            val path = auth.uid?.let { db.collection("discos").document(it).collection("Products") }
            val documentProduct = path?.document()
            product.id = documentProduct?.id
            documentProduct?.id?.let { path.document(it).set(product).await() }
            ResourceRemote.success(data = product.id)
        } catch (e: FirebaseFirestoreException) {
            Log.d("CreateProduct", e.localizedMessage)
            ResourceRemote.error(message = e.localizedMessage)
        } catch (e: FirebaseFirestoreException) {
            Log.d("CreateProduct", e.localizedMessage)
            ResourceRemote.error(message = e.localizedMessage)
        }
    }

    suspend fun loadProducts(): ResourceRemote<QuerySnapshot> {
        return try {
            val docRef =
                auth.uid?.let { db.collection("discos").document(it).collection("Products") }
            val result = docRef?.get()?.await()
            ResourceRemote.success(data = result)
        } catch (e: FirebaseFirestoreException) {
            ResourceRemote.error(message = e.localizedMessage)
        } catch (e: FirebaseNetworkException) {
            ResourceRemote.error(message = e.localizedMessage)
        }
    }

    suspend fun deleteProduct(product_: Product): Any? {
        return auth.uid?.let {
           db.collection("discos").document(it).collection("Products")
                .document(product_.id.toString()).delete().await()
        }
    }

    suspend fun editProduct(product: Product): ResourceRemote<String> {
        return try {
            auth.uid?.let {
                db.collection("discos").document(it).collection("Products")
                    .document(product.id.toString()).delete().await()
            }
            val path = auth.uid?.let { db.collection("discos").document(it).collection("Products") }
            val documentProduct = path?.document(product.id.toString())
            documentProduct?.id?.let { path.document(it).set(product).await() }
            ResourceRemote.success(data = product.id)
        } catch (e: FirebaseFirestoreException) {
            Log.d("CreateProduct", e.localizedMessage)
            ResourceRemote.error(message = e.localizedMessage)
        } catch (e: FirebaseFirestoreException) {
            Log.d("CreateProduct", e.localizedMessage)
            ResourceRemote.error(message = e.localizedMessage)
        }
    }
}