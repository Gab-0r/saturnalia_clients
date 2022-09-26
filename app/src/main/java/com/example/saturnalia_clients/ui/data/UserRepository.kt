package com.example.saturnalia_clients.ui.data

import android.util.Log
import com.example.saturnalia_clients.ui.model.Disco
import com.example.saturnalia_clients.ui.model.User
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.rpc.context.AttributeContext
import kotlinx.coroutines.tasks.await

class UserRepository {

    private var auth: FirebaseAuth = Firebase.auth //Referencia a la pestaña de autenticación de firebase
    private var db = Firebase.firestore

    suspend fun registerUser(name_: String, email_: String, pass_: String, confPass_: String) : ResourceRemote <String> {
        return try {
            val result = auth.createUserWithEmailAndPassword(email_, pass_).await()
            ResourceRemote.success(data = result.user?.uid)
        } catch (e: FirebaseAuthException) {
            Log.d("Register", e.localizedMessage)
            ResourceRemote.error(message = e.localizedMessage)
        } catch (e: FirebaseNetworkException){
            Log.d("Register", e.localizedMessage)
            ResourceRemote.error(message = e.localizedMessage)
        }
    }

    suspend fun loginUser(email_: String, pass_: String): ResourceRemote<String?> {
        return try {
            val result = auth.signInWithEmailAndPassword(email_, pass_).await()
            ResourceRemote.success(data = result.user?.uid)
        }catch(e: FirebaseAuthException){
            Log.d("Login", e.localizedMessage)
            ResourceRemote.error(message = e.localizedMessage)
        }catch (e: FirebaseNetworkException){
            Log.d("LoginNetwork", e.localizedMessage)
            ResourceRemote.error(message = e.localizedMessage)
        }
    }
    suspend fun createUser(disco_: Disco): ResourceRemote<String>{
        return try {
            disco_.uid?.let { db.collection("discos").document(it).set(disco_).await() }
            ResourceRemote.success(data = disco_.uid)
        } catch (e: FirebaseFirestoreException) {
            Log.d("Register", e.localizedMessage)
            ResourceRemote.error(message = e.localizedMessage)
        } catch (e: FirebaseFirestoreException){
            Log.d("Register", e.localizedMessage)
            ResourceRemote.error(message = e.localizedMessage)
        }
    }

    suspend fun createField(disco_: Disco) : ResourceRemote<String>{
        return try {
            disco_.uid?.let { db.collection("discos").document(it).collection("Info").document(it).set(disco_).await() }
            ResourceRemote.success(data = disco_.uid)
        } catch (e: FirebaseFirestoreException){
            ResourceRemote.error(message = e.localizedMessage)
        } catch (e: FirebaseNetworkException){
            ResourceRemote.error(message = e.localizedMessage)
        }
    }
}