package com.example.comicreaderapp.Common

import com.example.comicreaderapp.models.Chapter
import com.example.comicreaderapp.models.Comic

object Common {
    fun formatString(name: String): String {
        val chapterName = StringBuilder(if(name.length  > 15)name.substring(0,15)+"...." else name)
        return chapterName.toString()
    }

    var chapter_index :Int = -1
    lateinit var selected_chapter: Chapter
    lateinit var chapterList: List<Chapter>
    var selected_comic: Comic? = null
    var comicList: List<Comic> = ArrayList<Comic>()
}