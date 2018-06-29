package com.solution.alnahar.newsreaderkotlin

import android.app.AlertDialog
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.solution.alnahar.newsreaderkotlin.Adapter.ListSourceAdapter
import com.solution.alnahar.newsreaderkotlin.R.id.rvSourceNews
import com.solution.alnahar.newsreaderkotlin.R.id.swipe_to_Refresh
import com.solution.alnahar.newsreaderkotlin.common.Common
import com.solution.alnahar.newsreaderkotlin.model.HomeFeed
import com.solution.alnahar.newsreaderkotlin.model.Videos
import com.solution.alnahar.newsreaderkotlin.model.Website
import dmax.dialog.SpotsDialog
import io.paperdb.Paper
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class MainActivity : AppCompatActivity() {


    private lateinit var newsService: NewsService;
    private lateinit var dialog:AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        rvSourceNews.layoutManager = LinearLayoutManager(this)
        // rvSourceNews.adapter= ListSourceAdapter(this, Website)

        Paper.init(this);


        dialog =SpotsDialog(this)
        dialog.show()


        swipe_to_Refresh.setOnRefreshListener {


            loadWebsiteSource(true)
        }


        loadWebsiteSource(false)


    }


    fun loadWebsiteSource(isRefresh: Boolean) {


        newsService = Common.newsService


        var adapter: ListSourceAdapter

        if (!isRefresh) {
            var cache = Paper.book().read<String>("cache")

            if (cache != null && !cache.isBlank() && cache != "null") {

                 dialog.dismiss()

                var website = Gson().fromJson<Website>(cache, Website::class.java)
                adapter = ListSourceAdapter(baseContext, website)
                adapter.notifyDataSetChanged()
                rvSourceNews.adapter = adapter;
            } else {



                newsService.sources.enqueue(object : retrofit2.Callback<Website> {
                    override fun onFailure(call: Call<Website>?, t: Throwable?) {
                        call.toString()
                          dialog.dismiss()

                        var errorMess: String = t!!.localizedMessage

                        println("seomething went wrong")
                        Toast.makeText(baseContext, "Failed ", Toast.LENGTH_SHORT).show()


                    }

                    override fun onResponse(call: Call<Website>?, response: Response<Website>?) {
                        println(response?.body())
                        var lp = response?.body()

                         dialog.dismiss()


                        adapter = ListSourceAdapter(baseContext, response!!.body()!!)
                        adapter.notifyDataSetChanged()
                        rvSourceNews.adapter = adapter;

                        Paper.book().write("cache", Gson().toJson(response!!.body()!!));
                    }

                })


            }



        }

        else
        {

            swipe_to_Refresh.isRefreshing=true

            newsService.sources.enqueue(object : retrofit2.Callback<Website> {
                override fun onFailure(call: Call<Website>?, t: Throwable?) {
                    call.toString()
                      dialog.dismiss()

                    var errorMess: String = t!!.localizedMessage

                    println("seomething went wrong")
                    Toast.makeText(baseContext, "Failed ", Toast.LENGTH_SHORT).show()
                    swipe_to_Refresh.isRefreshing=false



                }

                override fun onResponse(call: Call<Website>?, response: Response<Website>?) {
                    println(response?.body())
                    var lp = response?.body()
                     dialog.dismiss()


                    adapter = ListSourceAdapter(baseContext, response!!.body()!!)
                    adapter.notifyDataSetChanged()
                    rvSourceNews.adapter = adapter;

                    Paper.book().write("cache", Gson().toJson(response!!.body()!!));
                   swipe_to_Refresh.isRefreshing=false
                }

            })

        }
    }
}
