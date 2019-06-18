package com.example.domain.model

enum class Sex {

    Man, Woman;

    companion object{
        fun getAll(): List<Sex>{
            return arrayListOf(Man, Woman)
        }

        fun getByString(sex: String): Sex{
            return when(sex){
                Man.name -> Man
                Woman.name -> Woman
                else -> Man
            }
        }
    }
}