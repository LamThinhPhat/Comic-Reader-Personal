package com.example.comicreaderapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.comicreaderapp.Common.Common
import com.example.comicreaderapp.adapter.MyViewPagerAdapter
import com.example.comicreaderapp.models.Chapter
import com.wajahatkarim3.easyflipviewpager.BookFlipPageTransformer
import kotlinx.android.synthetic.main.activity_view_comic.*

class ViewComicActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_comic)

        backchap.setOnClickListener{
            if (Common.chapter_index ==0)
            {
                Toast.makeText(this@ViewComicActivity, "You are on first chapter", Toast.LENGTH_SHORT)
                    .show()
            }
            else
            {
                Common.chapter_index--
                fetchLinks(Common.chapterList[Common.chapter_index])
            }
        }

        nextchap.setOnClickListener{
            if (Common.chapter_index == Common.chapterList.size - 1)
            {
                Toast.makeText(this@ViewComicActivity, "You are on last chapter", Toast.LENGTH_SHORT)
                    .show()
            }
            else
            {
                Common.chapter_index++
                fetchLinks(Common.chapterList[Common.chapter_index])
            }
        }

        fetchLinks(Common.selected_chapter!!)
    }

    private fun fetchLinks(chapter: Chapter) {
        if(chapter.Links !=  null)
        {
            if(chapter.Links!!.size != 0)
            {
                val adapter = MyViewPagerAdapter(baseContext, chapter.Links!!)
                view_page.adapter = adapter
                text_chapter_name_view_comic.text = Common.formatString(Common.selected_chapter!!.Name!!)

                val bookFlipTransformer = BookFlipPageTransformer() // Animation Flip Book
                bookFlipTransformer.scaleAmountPercent = 10f
                view_page.setPageTransformer(true, bookFlipTransformer)
            }
            else
            {
                Toast.makeText(this@ViewComicActivity, "No more page", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        else
        {
            Toast.makeText(this@ViewComicActivity, "You are on lastest chapter", Toast.LENGTH_SHORT)
                .show()
        }
    }
}