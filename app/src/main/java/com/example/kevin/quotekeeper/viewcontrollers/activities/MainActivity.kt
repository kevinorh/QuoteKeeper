package com.example.kevin.quotekeeper.viewcontrollers.activities

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.example.kevin.quotekeeper.R
import com.example.kevin.quotekeeper.viewcontrollers.fragments.HomeFragment
import com.example.kevin.quotekeeper.viewcontrollers.fragments.QuoteFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        return@OnNavigationItemSelectedListener navigateTo(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.selectedItemId=R.id.navigation_home
    }
    private fun fragmentFor(item:MenuItem):Fragment{
        when (item.itemId) {
            R.id.navigation_home -> {
                return HomeFragment()
            }
            R.id.navigation_quotes -> {
               return QuoteFragment()
            }
        }
        return HomeFragment()
    }
    private fun navigateTo(item:MenuItem):Boolean{
        item.setChecked(true)
        return supportFragmentManager
                .beginTransaction()
                .replace(R.id.content,fragmentFor(item))
                .commit()>0
    }
}
