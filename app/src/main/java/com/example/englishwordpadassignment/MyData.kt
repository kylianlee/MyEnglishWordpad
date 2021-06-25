package com.example.englishwordpadassignment

data class MyData(val word:String, val meaning:String, var isSelected:Boolean, var isChecked:Boolean) {
    constructor():this("", "", false, false)
}