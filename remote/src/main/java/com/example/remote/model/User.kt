package com.example.remote.model

import com.google.gson.annotations.Expose

data class User(@Expose val username: String, @Expose val password: String)
