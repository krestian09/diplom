package com.example.domain.model

data class User (val login: String,
                 val height: Long,
                 val weight: Long,
                 val age: Int,
                 val sex: Sex,
                 val activityLevel: UserActivityLevel)