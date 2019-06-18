package com.example.calorieassistant.main.account

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.example.calorieassistant.App
import com.example.domain.model.UserPersonal
import com.example.shared.SharedPreferenceManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AccountViewModel : ViewModel(){

    val isEditableLiveData = MutableLiveData<Boolean>()
    val logOutLiveData = MutableLiveData<Boolean>()
    var editable = ObservableBoolean(false)
    val userPersonal = ObservableField<UserPersonal>()
    val username = ObservableField<String>()

    init{
        App.getInstance().sharedPreferanceManager.getString(SharedPreferenceManager.USER_NAME)?.let {
            username.set(it)
        }
        App.getInstance()
            .database
            .userDao()
            .get(App.getInstance().sharedPreferanceManager.getString(SharedPreferenceManager.USER_NAME)!!)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnSuccess {
                userPersonal.set(UserPersonal(it))
            }.subscribe()
    }


    fun onLogOutClicked(){
        App.getInstance().sharedPreferanceManager.saveString(SharedPreferenceManager.USER_NAME, null)
        logOutLiveData.value = true
    }

    fun onEditClicked(){
        editable.set(editable.get().not())
        isEditableLiveData.postValue(editable.get())
        if(!editable.get()) submit()
    }

    fun onHeightChanged(height: Long){
        userPersonal.get()?.height = height
    }

    fun onWeightChanged(weight: Long){
        userPersonal.get()?.weight = weight
    }

    fun onAgeChanged(age: Int){
        userPersonal.get()?.age = age
    }


    private fun submit(){
        userPersonal.get()?.let {
            username.get()?.let{name: String ->
                App.getInstance()
                    .database
                    .userDao()
                    .update(it.toDomain(name))
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .doOnSuccess {
                        print(it)
                    }
                    .subscribe()
            }
        }
    }
}