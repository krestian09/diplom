package com.example.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

import com.example.database.dao.UserDao
import com.example.database.model.User


@Database(entities = [User::class], version = 1)
abstract class AppDatabase: RoomDatabase(){

    abstract fun userDao(): UserDao

    companion object{
        fun getDatabase(context: Context, appDatabaseName: String): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, appDatabaseName).build()
        }
    }
}