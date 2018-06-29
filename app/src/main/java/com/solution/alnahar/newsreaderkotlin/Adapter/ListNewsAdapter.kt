package com.solution.alnahar.newsreaderkotlin.Adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.solution.alnahar.newsreaderkotlin.ListNewsActivity
import com.solution.alnahar.newsreaderkotlin.NewsDetailActivity
import com.solution.alnahar.newsreaderkotlin.R
import com.solution.alnahar.newsreaderkotlin.common.ISO86601Parser
import com.solution.alnahar.newsreaderkotlin.model.Article
import com.solution.alnahar.newsreaderkotlin.model.NewsModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_list_news.view.*
import java.text.ParseException
import java.util.*

public class ListNewsAdapter(val context:Context ,val listOfArticle:MutableList<Article>) : RecyclerView.Adapter<Myadapter>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myadapter {

        var layoutInflater = LayoutInflater.from(parent.context)
        var row = layoutInflater.inflate(R.layout.row_list_news, parent, false)

        return Myadapter(row)

    }

    override fun getItemCount(): Int {
        return listOfArticle.size
    }

    override fun onBindViewHolder(holder: Myadapter, position: Int) {

       var imagePah= listOfArticle[position].urlToImage
        try {
            if (!imagePah.isEmpty())
            {
                Picasso.get()
                        .load(listOfArticle[position].urlToImage)
                        .into(holder.customView.article_image);
            }
        }
        catch (e:Exception)
        {

        }




//        if (listOfArticle[position].title.length > 20) {
//            holder.customView.article_title.text = listOfArticle[position].title.substring(0, 20) + "......"
//        } else {

            holder.customView.article_title.text = listOfArticle[position].title

       // }
        if (listOfArticle[position].publishedAt != null) {
            var date: Date? = null
            try {

                date = ISO86601Parser.parser(listOfArticle[position].publishedAt)
                holder.customView.article_time.setReferenceTime(date.time)

            } catch (ex: ParseException) {

                ex.printStackTrace()
            }


        }

        holder.customView.cardView.setOnClickListener(View.OnClickListener {


            var intent=Intent(context,NewsDetailActivity::class.java)
            intent.putExtra("webUrl",listOfArticle[position].url)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            context.startActivity(intent)



        })


    }


}

class Myadapter(var customView: View) : RecyclerView.ViewHolder(customView) {


}
