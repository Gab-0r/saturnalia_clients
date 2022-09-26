package com.example.saturnalia_clients.ui.fragments.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saturnalia_clients.ui.data.ResourceRemote
import com.example.saturnalia_clients.ui.data.UserRepository
import com.example.saturnalia_clients.ui.model.Disco
import com.example.saturnalia_clients.ui.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {

    private val userRepository = UserRepository()///Repositorio (Fuente de datos  para la aplicación)
    private lateinit var disco: Disco

    private val _registerSuccess: MutableLiveData<String?> = MutableLiveData()
    val registerSuccess: LiveData<String?> = _registerSuccess

    private val _errorMsg: MutableLiveData<String?> = MutableLiveData()//Mensaje de error
    val erroMsg: LiveData<String?> = _errorMsg //Mensaje de error que se le pasa a la vista

    fun checkFields(name_: String, email_: String, pass_: String, confPass_: String) {
        if(name_.isEmpty() || email_.isEmpty() || pass_.isEmpty() || confPass_.isEmpty())
            _errorMsg.value = "Todos los campos deben ser llenados"
        else
            if(pass_ != confPass_)
                _errorMsg.value = "Las contraseñas deben de ser iguales"
            else
                if(pass_.length < 6)
                    _errorMsg.value = "La contraseña debe tener como mínimo 6 caracteres"
                else
                //Si se cumplen todas las validaciones se hace el Login
                //Lanzar corrutina para operacion en base de datos (Se usa el despachador IO)
                    viewModelScope.launch(Dispatchers.IO) {
                        var result = userRepository.registerUser(name_, email_, pass_, confPass_)
                        result.let {resourceRemote->
                            when(resourceRemote){
                                is ResourceRemote.success -> {
                                    disco = Disco(uid = result.data, name = name_, email = email_)
                                    createUser(disco)
                                    createField(disco)
                                    //_registerSuccess.postValue(result.data)
                                }
                                is ResourceRemote.error -> {
                                    var msg = result.message
                                    if(result.message == "The email address is already in use by another account.")
                                        msg = "El email que intenta registrar ya se encuentra en uso."

                                    else
                                        if(result.message == "The email address is badly formatted.")
                                            msg = "El email es incorrecto."
                                    _errorMsg.postValue(msg)
                                }
                                else -> {

                                }
                            }
                        }
                    }


    }

    private fun createField(disco: Disco) {
        viewModelScope.launch{
            val result = userRepository.createField(disco)
            result.let { resourceRemote ->
                when(resourceRemote){
                    is ResourceRemote.success ->{
                        _registerSuccess.postValue(result.data)
                        _errorMsg.postValue("Registro exitoso")
                    }
                    is ResourceRemote.error ->{
                        var msg = result.message
                        _errorMsg.postValue(msg)
                    }
                    else ->{
                        //don't use
                    }
                }
            }
        }
    }

    fun createUser(disco_: Disco) {
        viewModelScope.launch{
            val result = userRepository.createUser(disco_)
            result.let { resourceRemote ->
                when(resourceRemote){
                    is ResourceRemote.success ->{
                        _registerSuccess.postValue(result.data)
                        _errorMsg.postValue("Registro exitoso")
                    }
                    is ResourceRemote.error ->{
                        var msg = result.message
                        _errorMsg.postValue(msg)
                    }
                    else ->{
                        //don't use
                    }
                }
            }
        }
    }
}