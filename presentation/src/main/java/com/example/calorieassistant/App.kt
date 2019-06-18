package com.example.calorieassistant

import android.app.Application
import com.example.database.AppDatabase
import com.example.remote.RestApi
import com.example.shared.SharedPreferenceManager
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import java.util.concurrent.TimeUnit
import ru.terrakok.cicerone.NavigatorHolder




class App: Application(){

    lateinit var restApi: RestApi
    lateinit var database: AppDatabase
    lateinit var sharedPreferanceManager: SharedPreferenceManager
    private lateinit var cicerone: Cicerone<Router>

    companion object{
        private lateinit var instance: App
        fun getInstance(): App{
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        val okhttp = OkHttpClient().newBuilder()
            .callTimeout(20, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()
        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(okhttp)
            .baseUrl(RestApi.BASE_URL)
            .build()
        restApi = retrofit.create(RestApi::class.java)
        database = AppDatabase.getDatabase(this, "database")
        sharedPreferanceManager = SharedPreferenceManager(this)
        cicerone = Cicerone.create()
    }

    fun getNavigatorHolder(): NavigatorHolder {
        return cicerone.navigatorHolder
    }

    fun getRouter(): Router {
        return cicerone.router
    }
}