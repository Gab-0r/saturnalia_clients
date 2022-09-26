package com.example.saturnalia_clients.ui.fragments.editprofile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saturnalia_clients.ui.data.DiscoRepository
import com.example.saturnalia_clients.ui.data.ResourceRemote
import com.example.saturnalia_clients.ui.model.Disco
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditProfileViewModel : ViewModel() {

    private val discoRepository = DiscoRepository()

    private val _msg: MutableLiveData<String> = MutableLiveData()
    val msg: LiveData<String> = _msg

    private val _editProfileSuccess: MutableLiveData<String?> = MutableLiveData()
    val editProfileSuccess: LiveData<String?> = _editProfileSuccess


    fun checkFields(id_: String, name_: String, about_: String, phone_: String, email_: String, addres_: String) {
        if(name_.isEmpty() || about_.isEmpty() || phone_.isEmpty() || email_.isEmpty() || addres_.isEmpty())
            _msg.value = "Todos los campos deben ser llenados"
        else
            viewModelScope.launch(Dispatchers.IO){
                val disco = Disco(uid = id_, name = name_, about = about_, phone = phone_, email = email_, address = addres_)
                val result = discoRepository.editDisco(disco)
                result.let { resourceRemote ->
                    when(resourceRemote){
                        is ResourceRemote.success -> {
                            _editProfileSuccess.postValue(result.data)
                            _msg.postValue("Cambios guardados")
                        }
                        is ResourceRemote.error -> {
                            //TODO Mensajes de error con su traducción
                        }
                        else ->{
                            //Don't use
                        }
                    }
                }
            }
    }
}