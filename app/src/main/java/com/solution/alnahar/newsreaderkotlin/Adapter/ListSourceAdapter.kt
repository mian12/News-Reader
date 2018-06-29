package com.solution.alnahar.newsreaderkotlin.Adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.solution.alnahar.newsreaderkotlin.ListNewsActivity
import com.solution.alnahar.newsreaderkotlin.R
import com.solution.alnahar.newsreaderkotlin.common.ItemClickListener
import com.solution.alnahar.newsreaderkotlin.model.Website
import kotlinx.android.synthetic.main.row_source_news.view.*

class ListSourceAdapter(var mContext: Context, var website: Website) : RecyclerView.Adapter<MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        var row = layoutInflater.inflate(R.layout.row_source_news, parent, false)

        return MyViewHolder(row)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.customView.newsTtile.text = website.sources[position].name


        holder.customView.cardView.setOnClickListener(View.OnClickListener {

            //Toast.makeText(mContext, "ciked.." + position, Toast.LENGTH_SHORT).show()

            var intent= Intent(mContext, ListNewsActivity::class.java)
            intent.putExtra("source",website.sources[position].id)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent)

        })

//        holder.setItemClickListner(object :ItemClickListener{
//            override fun clickEvent(view: View, pos: Int) {
//
//                Toast.makeText(mContext, "ciked interface.." + pos, Toast.LENGTH_SHORT).show()
//
//                var intent= Intent(mContext, ListNewsActivity::class.java)
//                intent.putExtra("source",website.sources[position].id)
//                mContext.startActivity(intent)
//
//            }
//
//        })

    }

    override fun getItemCount(): Int {

        return website.sources!!.count()

    }


}

class MyViewHolder(var customView: View) : RecyclerView.ViewHolder(customView), View.OnClickListener {

    private lateinit var itemClickListener: ItemClickListener

    init {
        customView.setOnClickListener(this)
    }

    fun setItemClickListner(itemClikListener: ItemClickListener) {

        this.itemClickListener = itemClikListener
    }

    override fun onClick(v: View?) {
        itemClickListener.clickEvent(v!!, adapterPosition)

    }


}