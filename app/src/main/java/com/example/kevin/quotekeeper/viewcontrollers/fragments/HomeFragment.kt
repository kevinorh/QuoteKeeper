package com.example.kevin.quotekeeper.viewcontrollers.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.androidnetworking.error.ANError

import com.example.kevin.quotekeeper.R
import com.example.kevin.quotekeeper.models.BookMark
import com.example.kevin.quotekeeper.models.Quote
import com.example.kevin.quotekeeper.network.talaikisApi
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment() {
    lateinit var quoteTextView: TextView
    lateinit var authorTextView: TextView
    lateinit var catTextView: TextView
    lateinit var moreButton:Button
    lateinit var markButton:ImageView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_home, container, false)
        quoteTextView=view.quoteTextView
        authorTextView=view.authorTextView
        catTextView=view.catTextView
        moreButton=view.moreButton
        markButton=view.bookmarkImageView
        talaikisApi.getRandomQuote(
                {response -> handlerResponse(response)},
                {error -> handlerError(error)}
        )
        moreButton.setOnClickListener {_ ->
            talaikisApi.getRandomQuote(
                    {response -> handlerResponse(response)},
                    {error -> handlerError(error)}
            )
        }
        markButton.setOnClickListener {_ ->
            val isBookmark = BookMark.existsOnDB(quoteTextView.text.toString())
            if(isBookmark){
                BookMark.deleteOne(quoteTextView.text.toString())
            }
            else{
                BookMark.saveOne(Quote(quoteTextView.text.toString(),
                        authorTextView.text.toString(),
                        catTextView.text.toString()))
            }
            markButton.setImageResource(imageResourceFor(!isBookmark))
        }

        return view
    }
    private fun handlerResponse(response: Quote?){
        quoteTextView.text="''${response!!.quote}''"
        authorTextView.text= response.author
        catTextView.text= response.cat
        markButton.setImageResource(imageResourceFor(BookMark.existsOnDB(quoteTextView.text.toString())))
    }
    private fun handlerError(anError: ANError?){
        Log.d("QuoteKeeper",anError!!.message)
    }
    fun imageResourceFor(isBookmarked: Boolean) : Int{
        return if(isBookmarked){
            R.drawable.ic_bookmark_black_24dp
        } else {
            R.drawable.ic_bookmark_border_black_24dp
        }
    }

}
