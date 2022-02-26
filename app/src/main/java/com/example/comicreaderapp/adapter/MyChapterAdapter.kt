package com.example.comicreaderapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.comicreaderapp.Interface.IRecycleClick
import com.example.comicreaderapp.R
import com.example.comicreaderapp.models.Chapter
import java.lang.StringBuilder

class MyChapterAdapter (internal var context: Context,
                        internal var chapterList: List<Chapter>): RecyclerView.Adapter<MyChapterAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {

        internal var txt_chapter_num:TextView
        internal lateinit var iRecyclerClick: IRecycleClick

        init{
            txt_chapter_num = itemView.findViewById(R.id.txt_chapter_num) as TextView

            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            iRecyclerClick.OnClick(p0!!, adapterPosition)
        }

        fun setClick(iRecycleClick: IRecycleClick)
        {
            this.iRecyclerClick = iRecycleClick
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemVIew = LayoutInflater.from(context).inflate(R.layout.chapter_item,parent,false)
        return MyViewHolder(itemVIew)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.txt_chapter_num.text = StringBuilder(chapterList[position].Name)

        holder.setClick(object :IRecycleClick{
            override fun OnClick(view: View, position: Int) {

            }
        })
    }

    override fun getItemCount(): Int {
        return chapterList.size
    }
}