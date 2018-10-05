package com.example.kevin.quotekeeper

import android.app.Application
import com.androidnetworking.AndroidNetworking
import com.orm.SugarApp

class QuoteKeeperApp:SugarApp(){
    override fun onCreate() {
        super.onCreate()
        AndroidNetworking.initialize(applicationContext)
    }
}