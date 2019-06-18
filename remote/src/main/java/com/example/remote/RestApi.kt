package com.example.remote

import com.example.remote.model.Dish
import com.example.remote.model.User
import com.example.remote.model.UserVerifyResponse
import io.reactivex.Single
import jdk.nashorn.internal.objects.annotations.Getter
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RestApi{

    companion object {
//        const val BASE_URL = "http://10.0.2.2:8080"
        const val BASE_URL = "https://dplm-server.herokuapp.com"
    }

    @POST("sign/in")
    fun signIn(@Body user: User): Single<UserVerifyResponse>

    @POST("sign/up")
    fun signUp(@Body user: User): Single<UserVerifyResponse>

    @GET("dishes")
    fun getDishes(@Query("part") part: String): Single<List<Dish>>
}
