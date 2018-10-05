package com.example.kevin.quotekeeper.models

import com.orm.SugarRecord

class BookMark : SugarRecord(){
    var quote: String = ""
    var author: String = ""
    var cat: String = ""

    companion object {
        fun listAllQuotes(): List<BookMark>{
            return listAll(BookMark::class.java)
        }

        fun existsOnDB(quote: String) : Boolean{
            return SugarRecord.find(BookMark::class.java,
                    "quote = ?",
                    quote).size > 0
        }

        fun saveOne(quote: Quote){
            val bookmark = BookMark()
            bookmark.quote = quote.quote!!
            bookmark.author = quote.author!!
            bookmark.cat = quote.cat!!
            bookmark.save()
        }

        fun deleteOne(quote: String) {
            val bookmarks = SugarRecord.find(BookMark::class.java,
                    "quote = ?",
                    quote)
            bookmarks.forEach { it.delete() }
        }
    }
}