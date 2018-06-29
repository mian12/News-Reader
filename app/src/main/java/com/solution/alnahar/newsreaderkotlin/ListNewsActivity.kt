package com.solution.alnahar.newsreaderkotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.solution.alnahar.newsreaderkotlin.Adapter.ListNewsAdapter
import com.solution.alnahar.newsreaderkotlin.common.Common
import com.solution.alnahar.newsreaderkotlin.model.NewsModel
import com.squareup.picasso.Picasso
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_list_news.*
import kotlinx.android.synthetic.main.row_list_news.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListNewsActivity : AppCompatActivity() {

    var source = ""
    var webHotUrl: String? = ""

    lateinit var dialog: android.app.AlertDialog
    lateinit var mService: NewsService
    lateinit var adapter: ListNewsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_news)

        dialog = SpotsDialog(this)
        dialog.show()


        mService = Common.newsService

        diagnoalLayout.setOnClickListener(View.OnClickListener {

            var intent= Intent(this,NewsDetailActivity::class.java)
            intent.putExtra("webUrl",webHotUrl)
            startActivity(intent)


        })

        if (intent != null) {
            source = intent.getStringExtra("source")
            if (!source.isEmpty()) {
                loadNews(source, false)
            }
        }


        swipe_to_Refresh.setOnRefreshListener {


            loadNews(source, true)

        }
    }

    private fun loadNews(source: String?, isRefreshed: Boolean) {
        if (isRefreshed) {
            mService.getNewsFromSource(Common.getNews(source!!)).enqueue(object : Callback<NewsModel> {
                override fun onFailure(call: Call<NewsModel>?, t: Throwable?) {

                    dialog.dismiss()

                }

                override fun onResponse(call: Call<NewsModel>?, response: Response<NewsModel>?) {


                    dialog.dismiss()

                    Picasso.get()
                            .load(response?.body()!!.articles[0].urlToImage)
                            .into(top_image);

                    top_title.text = response?.body()!!.articles[0].title
                    top_author.text = response?.body()!!.articles[0].author

                    webHotUrl = response?.body()!!.articles[0].url

                    val removeFirstItem = response?.body()!!.articles
                    removeFirstItem.removeAt(0);
                    adapter = ListNewsAdapter(baseContext, removeFirstItem)
                    adapter.notifyDataSetChanged()
                    swipe_to_Refresh.isRefreshing = false


                }

            })
        } else {

            mService.getNewsFromSource(Common.getNews(source!!)).enqueue(object : Callback<NewsModel> {
                override fun onFailure(call: Call<NewsModel>?, t: Throwable?) {
                    dialog.dismiss()

                }

                override fun onResponse(call: Call<NewsModel>?, response: Response<NewsModel>?) {


                    dialog.dismiss()

                    var imagePath=response?.body()!!.articles[0].urlToImage

                    if (imagePath!=null)
                    {
                        Picasso.get()
                                .load(response?.body()!!.articles[0].urlToImage)
                                .into(top_image);
                    }


                    top_title.text = response?.body()!!.articles[0].title
                    top_author.text = response?.body()!!.articles[0].author

                    webHotUrl = response?.body()!!.articles[0].url

                    var removeFirstItem = response?.body()!!.articles
                    removeFirstItem.removeAt(0);
                    adapter = ListNewsAdapter(baseContext, removeFirstItem)
                    adapter.notifyDataSetChanged()
                    list_news.layoutManager = LinearLayoutManager(baseContext)
                    list_news.adapter = adapter

                    swipe_to_Refresh.isRefreshing = false


                }

            })

        }

    }
}
