package com.example.kevin.quotekeeper.models

import android.os.Bundle
import com.orm.SugarRecord

data class Quote(
        val quote:String?="",
        val author:String?="",
        val cat:String?=""
){
    fun isBookmarked() : Boolean{
        return SugarRecord.find(BookMark::class.java,
                "quote = ?",
                quote).size > 0
    }
}