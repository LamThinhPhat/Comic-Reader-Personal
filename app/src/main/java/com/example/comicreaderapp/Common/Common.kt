package com.example.comicreaderapp.Common

import com.example.comicreaderapp.models.Chapter
import com.example.comicreaderapp.models.Comic

object Common {
    lateinit var chapterList: List<Chapter>
    var selected_comic: Comic? = null
    var comicList: List<Comic> = ArrayList<Comic>()
}