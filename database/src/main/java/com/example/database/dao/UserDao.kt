package com.example.database.dao



import android.arch.persistence.room.*
import com.example.database.model.User
import io.reactivex.Completable
import io.reactivex.Single

@Dao
abstract class UserDao{

    @Update(onConflict = OnConflictStrategy.REPLACE)
    internal abstract fun updateUser(user: User): Int

    @Insert
    internal abstract fun insertUser(user: User): Long

    @Query("Select * from user where login = :username")
    internal abstract fun getUser(username: String): Single<User>

    @Query("Select * from user")
    internal abstract fun getUsers(): Single<List<User>>

    fun get(): Single<List<com.example.domain.model.User>>{
        return getUsers().map {
            val list = ArrayList<com.example.domain.model.User>()
            it.map { list.add(it.toDomain()) }
            list
        }
    }

    fun get(username: String): Single<com.example.domain.model.User>{
        return getUser(username).map { it.toDomain() }
    }

    fun update(user: com.example.domain.model.User): Single<Boolean>{
        return Single.fromCallable {
            val userDatabase = User.fromDomain(user)
            updateUser(userDatabase) > 0 }
    }

    fun insert(user: com.example.domain.model.User): Single<Boolean>{
        return Single.fromCallable{
            val userDatabase = User.fromDomain(user)
            insertUser(userDatabase) > 0
        }
    }
}