
package com.example.comicreaderapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.comicreaderapp.Common.Common
import com.example.comicreaderapp.adapter.MyChapterAdapter
import com.example.comicreaderapp.models.Comic
import kotlinx.android.synthetic.main.activity_chapter.*
import kotlinx.android.synthetic.main.chapter_item.*
import java.lang.StringBuilder

class ChapterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chapter)

        toolbar.title = Common.selected_comic!!.Name
        toolbar.setNavigationIcon(R.drawable.ic_back)

        toolbar.setNavigationOnClickListener{
            finish() //go back to MainActivity
        }

        recycle_chapter.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this@ChapterActivity)

        recycle_chapter.layoutManager = layoutManager
        recycle_chapter.addItemDecoration(DividerItemDecoration(this, layoutManager.orientation))

        fetchChapter(Common.selected_comic!!)

    }

    private fun fetchChapter(comic: Comic) {
        Common.chapterList = comic.Chapters!!
        txt_chapter_name.text = StringBuilder("Chapter (")
            .append(comic.Chapters!!.size)
            .append(") ")

        recycle_chapter.adapter = MyChapterAdapter(this, Common.chapterList)

    }
}