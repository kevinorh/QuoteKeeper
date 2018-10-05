package com.example.kevin.quotekeeper.viewcontrollers.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kevin.quotekeeper.R
import com.example.kevin.quotekeeper.models.BookMark
import com.example.kevin.quotekeeper.models.Quote
import kotlinx.android.synthetic.main.item_quote.view.*

class QuoteAdapter(val quotes:ArrayList<BookMark>,val context: Context):RecyclerView.Adapter<QuoteAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, position1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_quote,parent,false))
    }

    override fun getItemCount(): Int {
        return quotes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val quote=quotes.get(position)
        holder.updateFrom(quote)
    }

    class ViewHolder(view:View):RecyclerView.ViewHolder(view) {
        val quoteTextView=view.quoteTextView
        val authorTextView=view.authorTextView
        val catTextView=view.catTextView
        val quoteLayout=view.quoteLayout
        fun updateFrom(quote:BookMark){
            quoteTextView.text=quote.quote
            authorTextView.text=quote.author
            catTextView.text=quote.cat
        }
    }

}