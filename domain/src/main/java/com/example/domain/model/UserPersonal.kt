package com.example.domain.model

import com.example.domain.model.error.ValidationUserPersonalError

data class UserPersonal(var height: Long,
                        var weight: Long,
                        var age: Int,
                        var sex: Sex,
                        var activityLevel: UserActivityLevel
){

    constructor(): this(0, 0, 0, Sex.Man, UserActivityLevel.No)

    constructor(user: User): this(user.height, user.weight, user.age, user.sex, user.activityLevel)

    fun isValid(): ValidationUserPersonalError?{
        if(height == 0L) return ValidationUserPersonalError.HEIGHT_ZERO
        if(weight == 0L) return ValidationUserPersonalError.WEIGHT_ZERO
        if(age == 0) return ValidationUserPersonalError.AGE_ZERO
        return null
    }

    fun toDomain(userName: String) = User(userName, height, weight, age, sex, activityLevel)

}