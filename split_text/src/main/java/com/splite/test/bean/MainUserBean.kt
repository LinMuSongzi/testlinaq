package com.splite.test.bean

data class MainUserBean(val name: String, val gender: Int, val school: String, val phoneNumber: String) {


    fun getGenderStr() = when (gender) {
        1 -> {
            "男"
        }
        2 -> {
            "女"
        }
        else -> {
            ""
        }
    }


}