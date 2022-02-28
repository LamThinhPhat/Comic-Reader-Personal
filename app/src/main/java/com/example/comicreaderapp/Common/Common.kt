package com.example.comicreaderapp.Common

import com.example.comicreaderapp.models.Chapter
import com.example.comicreaderapp.models.Comic

object Common {
    fun formatString(name: String): String {
        val chapterName = StringBuilder(if(name.length  > 15)name.substring(0,15)+"...." else name)
        return chapterName.toString()
    }

    var categories = arrayOf("Action", "Adult", "Adventure", "Comedy", "Completed", "Cooking", "Doujinshi", "Drama", "Drop", "Ecchi", "Fantasy", "Gender bender", "Harem", "Historical", "Horror", "Jose", "Latest", "Manhua", "Manhwa", "Material arts", "Mature", "Mecha", "Medical", "Mystery", "Newest", "One shot", "Ongoing", "Psychological", "Romance", "School life", "Sci fi", "Seinen", "Shoujo", "Shoujo a", "Shounen", "Shounen ai", "Slice of life", "Smut", "Sports", "Superhero", "Supernatural", "Top Read", "Tragedy", "Webtoons", "Yaoi", "Yuri")
    var chapter_index :Int = -1
    lateinit var selected_chapter: Chapter
    lateinit var chapterList: List<Chapter>
    var selected_comic: Comic? = null
    var comicList: List<Comic> = ArrayList<Comic>()
}