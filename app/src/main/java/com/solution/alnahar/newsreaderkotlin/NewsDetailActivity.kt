package com.solution.alnahar.newsreaderkotlin

import android.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_news_detail.*

class NewsDetailActivity : AppCompatActivity() {

    lateinit  var alertDailog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)




        alertDailog = SpotsDialog(this)
        alertDailog.setTitle("please wait")
        alertDailog.show()

        val link = intent.getStringExtra("webUrl")

        webView.settings.javaScriptEnabled = true
        webView.webChromeClient= WebChromeClient()
        webView.webViewClient=object :WebViewClient(){

            override fun onPageFinished(view: WebView?, url: String?) {
                alertDailog.dismiss();
            }

        }

        webView.loadUrl(link)
    }
}
