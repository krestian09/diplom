package com.example.calorieassistant.sign.up

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import android.arch.lifecycle.ViewModel
import com.example.calorieassistant.App
import com.example.shared.SharedPreferenceManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SignUpViewModel: ViewModel() {
    private lateinit var userAuthorization: com.example.domain.model.UserAuthorization

    fun setUser(userAuthorization: com.example.domain.model.UserAuthorization) {
        this.userAuthorization = userAuthorization
    }

    fun onButtonSignUpClicked(): LiveData<Boolean> {
        return LiveDataReactiveStreams.fromPublisher(
            App.getInstance().restApi.signUp(userAuthorization.toRemote())
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