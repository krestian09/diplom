package com.example.remote.model

import com.google.gson.annotations.Expose

data class UserVerifyResponse(@Expose val token: Token?, @Expose val error: String?)