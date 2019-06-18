package com.example.database.dao

import android.arch.persistence.room.*
import com.example.database.model.UserStatistic
import io.reactivex.Single

@Dao
abstract class UserStatisticDao(){

    @Insert
    internal abstract fun insertUserStatistic(statistic: UserStatistic): Int

    @Delete
    internal abstract fun deleteUserStatistic(statistic: UserStatistic): Int

    @Query("Select * from userStatistic where name = :name")
    internal abstract fun getByName(name: String): Single<UserStatistic>

}