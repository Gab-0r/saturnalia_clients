package com.example.saturnalia_clients.ui.fragments.login

import androidx.lifecycle.*
import com.example.saturnalia_clients.ui.data.ResourceRemote
import com.example.saturnalia_clients.ui.data.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val userRepository = UserRepository()///Repositorio (Fuente de datos  para la aplicación)

    private val _errorMsg: MutableLiveData<String?> = MutableLiveData()//Mensaje de error
    val erroMsg: LiveData<String?> = _errorMsg //Mensaje de error que se le pasa a la vista

    private val _loginSuccess: MutableLiveData<String?> = MutableLiveData()
    val loginSuccess: LiveData<String?> = _loginSuccess

    fun checkFields(email_: String, pass_: String) {
        if(email_.isEmpty() || pass_.isEmpty())
            _errorMsg.value = "Todos los campos deben ser llenados"
        if(pass_.length < 6)
            _errorMsg.value = "La contraseña debe tener como mínimo 6 caracteres"
        else
            GlobalScope.launch(Dispatchers.IO){
                val result = userRepository.loginUser(email_, pass_)
                result.let{resourceRemote ->
                    when(resourceRemote){
                        is ResourceRemote.success ->{
                            _loginSuccess.postValue(result.data)
                            _errorMsg.postValue("Bienvenido")
                        }
                        is ResourceRemote.error -> {
                            var msg = result.message

                        }
                        else -> {}
                    }
                }

            }
    }
}