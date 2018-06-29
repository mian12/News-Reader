package com.solution.alnahar.newsreaderkotlin.common

import com.solution.alnahar.newsreaderkotlin.NewsService
import com.solution.alnahar.newsreaderkotlin.remote.RetrofitClient
import okhttp3.OkHttpClient
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSession


object  Common{


    //https://newsapi.org/v2/sources?apiKey=d53a4e0e15634c84a4f84183f5968603

    var BASE_URL="https://newsapi.org/"
    var API_KEY="7675a838a8c2461795dfc4ed7abcacd6"

    //var BASE_URL="https://api.letsbuildthatapp.com/"


    //https://api.letsbuildthatapp.com/youtube/home_feed"


    //https://newsapi.org/v2/sources?apiKey=d53a4e0e15634c84a4f84183f5968603


   val  newsService
       get()= RetrofitClient.getClient(BASE_URL).create(NewsService::class.java)


    fun getNews(source:String):String{

       // https@ //newsapi.org/v2/top-headlines?sources=bbc-news&apiKey=7675a838a8c2461795dfc4ed7abcacd6

        val apiUrl=StringBuilder("https://newsapi.org/v2/top-headlines?sources=")
                .append(source)
                .append("&apiKey=")
                .append(API_KEY)
                .toString()

       return apiUrl
    }




}


//