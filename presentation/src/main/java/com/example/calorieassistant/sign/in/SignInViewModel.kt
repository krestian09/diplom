package com.example.calorieassistant.sign.`in`

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.calorieassistant.App
import com.example.shared.SharedPreferenceManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SignInViewModel : ViewModel() {

    private lateinit var userAuthorization: com.example.domain.model.UserAuthorization
    val liveDataAuthorizationCompleted = MutableLiveData<Boolean>()

    init{
        App.getInstance().sharedPreferanceManager.getString(SharedPreferenceManager.USER_NAME).let{
            if(!it.isNullOrBlank()) liveDataAuthorizationCompleted.postValue(true)
        }
    }

    fun setUser(userAuthorization: com.example.domain.model.UserAuthorization) {
        this.userAuthorization = userAuthorization
    }

    fun onButtonLoginClicked(): LiveData<Boolean> {
        return LiveDataReactiveStreams.fromPublisher(App.getInstance().restApi.signIn(userAuthorization.toRemote())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .map {
                it.token?.let { return@map saveUser() }
                it.error?.let { return@map false }
                false
            }
            .onErrorReturn {
                false
            }
            .toFlowable())
    }

    private fun saveUser(): Boolean{
        return App.getInstance().sharedPreferanceManager.saveString(SharedPreferenceManager.USER_NAME, userAuthorization.name)
    }
}