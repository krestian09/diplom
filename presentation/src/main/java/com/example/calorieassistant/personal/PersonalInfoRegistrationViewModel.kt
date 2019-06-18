package com.example.calorieassistant.personal

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.calorieassistant.App
import com.example.domain.model.Sex
import com.example.domain.model.UserActivityLevel
import com.example.domain.model.UserPersonal
import com.example.domain.model.error.ValidationUserPersonalError
import com.example.shared.SharedPreferenceManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PersonalInfoRegistrationViewModel: ViewModel(){

    private lateinit var userPersonal: com.example.domain.model.UserPersonal
    val submitData = MutableLiveData<com.example.domain.model.error.ValidationUserPersonalError?>()

    fun setUserPersonal(userPersonal: com.example.domain.model.UserPersonal){
        this.userPersonal = userPersonal
    }

    fun onHeightChanged(height: Long){
        userPersonal.height = height
    }

    fun onWeightChanged(weight: Long){
        userPersonal.weight = weight
    }

    fun onAgeChanged(age: Int){
        userPersonal.age = age
    }

    fun onUserSexChanged(sex: com.example.domain.model.Sex){
        userPersonal.sex = sex
    }

    fun onUserActivityLevelChanged(activity: com.example.domain.model.UserActivityLevel){
        userPersonal.activityLevel = activity
    }

    fun onButtonSubmitClicked(){
        if(userPersonal.isValid() == null){
                App.getInstance()
                    .database
                    .userDao()
                    .insert(userPersonal
                        .toDomain(App.getInstance().sharedPreferanceManager.getString(SharedPreferenceManager.USER_NAME)!!))
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe()
        } else{
            submitData.value = userPersonal.isValid()
        }

    }

}