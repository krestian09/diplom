package com.example.database.model


import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.example.domain.model.Sex
import com.example.domain.model.User
import com.example.domain.model.UserActivityLevel

@Entity
data class User(
    @PrimaryKey val login: String,
    val height: Long,
    val weight: Long,
    val age: Int,
    val sex: String,
    val activityLevel: String
) {

    fun toDomain(): User = User(
        login,
        height,
        weight,
        age,
        Sex.getByString(sex),
        UserActivityLevel.getByString(activityLevel)
    )

    companion object {
        fun fromDomain(user: User) = User(
            user.login,
            user.height,
            user.weight,
            user.age,
            user.sex.name,
            user.activityLevel.name
        )
    }
}