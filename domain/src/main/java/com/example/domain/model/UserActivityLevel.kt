package com.example.domain.model

enum class UserActivityLevel(val coefficient: Double){
    No(1.2),
    Low(1.35),
    Medium(1.55),
    High(1.725),
    Extreme(1.9);

    companion object{
        fun getAll(): List<UserActivityLevel>{
            return arrayListOf(
                No,
                Low,
                Medium,
                High,
                Extreme
            )
        }

        fun getByString(level: String): UserActivityLevel{
            return when(level){
                No.name -> No
                Low.name -> Low
                Medium.name -> Medium
                High.name -> High
                Extreme.name -> Extreme
                else -> Medium
            }
        }
    }

}