package com.example.viewmodeljetpack07.ViewModel

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.compose.runtime.remember
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import com.example.viewmodeljetpack07.Quote
import com.example.viewmodeljetpack07.mainViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

class QuoteViewModel(val context: Context) : ViewModel() {

    var quoteList: MutableList<Quote> = arrayListOf()

    // private var quoteList: Array<Quote> = emptyArray()
    private var index = 0;

    init {
        quoteList = getQutoesFromAsset()
    }


    private fun getQutoesFromAsset(): MutableList<Quote> {
        val inputStream = context.assets.open("quotes.json")
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        val type = object : TypeToken<List<Quote>>() {}.type
        return gson.fromJson(json, type)
    }

    fun getRandomQuote() = quoteList[index]


//    fun nextQuote(): Quote {
//        if (index < quoteList.lastIndex) {
//            index=index+1;
//        }
//
//        return quoteList[index]
//
//    }


//    fun previousQuote(): Quote {
//        if (index > 0) {
//            index=index-1;
//        }
//        return quoteList[index]
//
//    }

    fun nextQuote(): Quote {
        index = (index + 1) % quoteList.size
        return quoteList[index]
    }

    fun previousQuote(): Quote {
        index = (index - 1 + quoteList.size) % quoteList.size
        return quoteList[index]
    }
    fun onShare(context: Context,string: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TEXT, string)
        context.startActivity(intent)
    }

}
