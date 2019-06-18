package com.example.domain.model

import com.example.remote.model.User

data class UserAuthorization(var name: String, var password: String){

    fun toRemote(): User = User(name, password)
}