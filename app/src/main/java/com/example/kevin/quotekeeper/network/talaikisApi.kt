package com.example.kevin.quotekeeper.network

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.example.kevin.quotekeeper.models.Quote

class talaikisApi{
    companion object {
        val baseUrl="https://talaikis.com/api/quotes/random/"
        fun getRandomQuote(
                responseHandler: (Quote?) -> Unit,
                errorHandler: (ANError?)->Unit
        ){
            AndroidNetworking.get(talaikisApi.baseUrl)
                    .setPriority(Priority.LOW)
                    .build()
                    .getAsObject(Quote::class.java, object : ParsedRequestListener<Quote?>{
                        override fun onResponse(response: Quote?) {
                            responseHandler(response)
                        }

                        override fun onError(anError: ANError?) {
                            errorHandler(anError)
                        }

                    })
        }
    }
}