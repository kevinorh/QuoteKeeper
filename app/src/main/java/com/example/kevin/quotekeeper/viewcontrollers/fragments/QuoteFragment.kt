package com.example.kevin.quotekeeper.viewcontrollers.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import com.androidnetworking.error.ANError

import com.example.kevin.quotekeeper.R
import com.example.kevin.quotekeeper.models.BookMark
import com.example.kevin.quotekeeper.models.Quote
import com.example.kevin.quotekeeper.network.talaikisApi
import com.example.kevin.quotekeeper.viewcontrollers.adapters.QuoteAdapter
import kotlinx.android.synthetic.main.fragment_quote.view.*


class QuoteFragment : Fragment() {
    var quotes=ArrayList<BookMark>()
    lateinit var quotesRecyclerView: RecyclerView
    lateinit var quotesAdapter: QuoteAdapter
    lateinit var quoteLayoutManager:RecyclerView.LayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        if (BookMark.listAllQuotes().isEmpty()){
            return inflater.inflate(R.layout.fragment_quote_empty, container, false)
        }
        val view = inflater.inflate(R.layout.fragment_quote, container, false)
        quotes =   BookMark.listAllQuotes() as ArrayList<BookMark>
        quotesRecyclerView=view.quotesRecyclerView
        quotesAdapter= QuoteAdapter(quotes,view.context)
        quoteLayoutManager=GridLayoutManager(view.context,1)

        quotesRecyclerView.adapter=quotesAdapter
        quotesRecyclerView.layoutManager=quoteLayoutManager

        return view
    }

}
