package com.example.comicreaderapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.comicreaderapp.R
import com.example.comicreaderapp.models.Comic
import com.squareup.picasso.Picasso

class MyComicAdapter(internal var context:Context, internal var comicList:List<Comic>)
    :RecyclerView.Adapter<MyComicAdapter.MyViewHolder>() {
    class MyViewHolder(itemVIew: View) :RecyclerView.ViewHolder(itemVIew) {
        var imageView: ImageView
        var textView: TextView
        init {
            imageView = itemVIew.findViewById(R.id.comic_image) as ImageView
            textView = itemVIew.findViewById(R.id.comic_name) as TextView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemVIew = LayoutInflater.from(context).inflate(R.layout.comic_item,parent,false)
        return MyViewHolder(itemVIew)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Picasso.get().load(comicList[position].Image).into(holder.imageView)
        holder.textView.text = comicList[position].Name
    }

    override fun getItemCount(): Int {
        return comicList.size
    }

}