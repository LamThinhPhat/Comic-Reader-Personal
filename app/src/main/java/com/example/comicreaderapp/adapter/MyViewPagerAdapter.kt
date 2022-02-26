package com.example.comicreaderapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.example.comicreaderapp.R
import com.github.chrisbanes.photoview.PhotoView
import com.squareup.picasso.Picasso

class MyViewPagerAdapter (internal var context: Context, internal var linkList:List<String>):PagerAdapter() {
    internal var inflater:LayoutInflater
    init {
        inflater = LayoutInflater.from(context)
    }
    override fun getCount(): Int {
        return linkList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View )
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageLayout = inflater.inflate(R.layout.view_page_item,container,false)

        val page_image = imageLayout.findViewById<PhotoView>(R.id.page_comic)

        Picasso.get().load(linkList[position]).into(page_image)

        container.addView(imageLayout)

        return imageLayout

    }
}