package com.example.saturnalia_clients.ui.data

sealed class ResourceRemote<T> (
    var data: T? = null,
    var message: String? = null,
    var status: Status? = null,
    var errCode: Int? = null
){

    class success<T>(data: T?) : ResourceRemote<T>(data = data, status = Status.Success)
    class loading<T>(message: String?) : ResourceRemote<T>(message = message, status = Status.Loading)
    class error<T>(errCode: Int? = null, message: String? = null) : ResourceRemote<T>(errCode = errCode, message = message,status = Status.Error)
}

enum class Status{
    Success{
        override fun toString(): String {
            return this.name
        }
    },
    Error{
        override fun toString(): String {
            return this.name
        }
    },
    Loading{
        override fun toString(): String {
            return this.name
        }
    }
}