package com.example.comicreaderapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.comicreaderapp.ChapterActivity
import com.example.comicreaderapp.Common.Common
import com.example.comicreaderapp.Interface.IRecycleClick
import com.example.comicreaderapp.R
import com.example.comicreaderapp.models.Comic
import com.squareup.picasso.Picasso

class MyComicAdapter(internal var context:Context,
                     internal var comicList:List<Comic>) :RecyclerView.Adapter<MyComicAdapter.MyViewHolder>() {
    class MyViewHolder(itemVIew: View) :RecyclerView.ViewHolder(itemVIew), View.OnClickListener {
        var imageView: ImageView
        var textView: TextView
        lateinit var iRecyclerClick: IRecycleClick

        fun setClick(iRecycleClick: IRecycleClick)
        {
            this.iRecyclerClick = iRecycleClick
        }

        init {
            imageView = itemVIew.findViewById(R.id.comic_image) as ImageView
            textView = itemVIew.findViewById(R.id.comic_name) as TextView

            itemVIew.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            iRecyclerClick.OnClick(p0!!,adapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemVIew = LayoutInflater.from(context).inflate(R.layout.comic_item,parent,false)
        return MyViewHolder(itemVIew)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Picasso.get().load(comicList[position].Image).into(holder.imageView)
        holder.textView.text = comicList[position].Name

        holder.setClick(object:IRecycleClick{
            override fun OnClick(view: View, position: Int) {
                context.startActivity(Intent(context, ChapterActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
                Common.selected_comic = comicList[position]
            }
        })
    }

    override fun getItemCount(): Int {
        return comicList.size
    }

}