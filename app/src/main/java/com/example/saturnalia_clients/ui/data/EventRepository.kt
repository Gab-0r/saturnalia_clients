package com.example.saturnalia_clients.ui.data

import android.util.Log
import com.example.saturnalia_clients.ui.model.Event
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class EventRepository {
    private var auth: FirebaseAuth = Firebase.auth
    private var db = Firebase.firestore

    suspend fun createEvent(event: Event): ResourceRemote<String?> {
        return try {
            val path = auth.uid?.let { db.collection("discos").document(it).collection("Events") }
            val documentEvent = path?.document()
            event.id = documentEvent?.id
            documentEvent?.id?.let { path.document(it).set(event).await() }
            ResourceRemote.success(data = event.id)
        }catch (e: FirebaseFirestoreException){
            Log.d("CreateEvent", e.localizedMessage)
            ResourceRemote.error(message = e.localizedMessage)
        }catch (e: FirebaseNetworkException){
            Log.d("CreateEvent", e.localizedMessage)
            ResourceRemote.error(message = e.localizedMessage)
        }
    }

    suspend fun loadEvents(): ResourceRemote<QuerySnapshot> {
        return try {
            val docRef = auth.uid?.let { db.collection("discos").document(it).collection("Events") }
            val result = docRef?.get()?.await()
            ResourceRemote.success(data = result)
        } catch (e: FirebaseFirestoreException){
            ResourceRemote.error(message = e.localizedMessage)
        } catch (e: FirebaseNetworkException){
            ResourceRemote.error(message = e.localizedMessage)
        }
    }

    suspend fun deleteEvent(event: Event) : Any? {
        return auth.uid?.let {
            db.collection("discos").document(it).collection("Events")
                .document(event.id.toString()).delete().await()
        }
    }

    suspend fun editEvent(event: Event): ResourceRemote<String> {
        //Crear un nuevo evento
        return try {
            auth.uid?.let {
                db.collection("discos").document(it).collection("Events")
                    .document(event.id.toString()).delete().await()
            }
            val path = auth.uid?.let { db.collection("discos").document(it).collection("Events") }
            val documentEvent = path?.document(event.id.toString())
            documentEvent?.id?.let { path.document(it).set(event).await() }
            ResourceRemote.success(data = event.id)
        }catch (e: FirebaseFirestoreException){
            Log.d("CreateEvent", e.localizedMessage)
            ResourceRemote.error(message = e.localizedMessage)
        }catch (e: FirebaseNetworkException){
            Log.d("CreateEvent", e.localizedMessage)
            ResourceRemote.error(message = e.localizedMessage)
        }
    }

}

