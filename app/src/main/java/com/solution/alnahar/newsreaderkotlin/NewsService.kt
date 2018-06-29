package com.solution.alnahar.newsreaderkotlin

import com.solution.alnahar.newsreaderkotlin.model.HomeFeed
import com.solution.alnahar.newsreaderkotlin.model.NewsModel
import com.solution.alnahar.newsreaderkotlin.model.Videos
import com.solution.alnahar.newsreaderkotlin.model.Website
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface NewsService {

  //https://newsapi.org/v2/sources?apiKey=d53a4e0e15634c84a4f84183f5968603

  @get:GET("v2/sources?apiKey=7675a838a8c2461795dfc4ed7abcacd6")
    val sources:Call<Website>



  @get:GET("youtube/home_feed")
  var homeFeeedd:Call<HomeFeed>


  @GET
  fun getNewsFromSource(@Url url:String):Call<NewsModel>

}


