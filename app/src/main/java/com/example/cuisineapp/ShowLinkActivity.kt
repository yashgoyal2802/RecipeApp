package com.example.cuisineapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_show_link.*

class ShowLinkActivity  : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_link)

        val extras  = intent.extras
        if(extras != null)
        {
            val link = extras.get("link")  // the link which we passes in intent inside adapter

            webViewId.loadUrl(link.toString()) // accessing web using web view
        }
    }
}
