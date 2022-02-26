package com.example.comicreaderapp.Interface

import com.example.comicreaderapp.models.Comic

interface ComicLoadDoneListener {
    fun OnComicLoadDoneListener(comicList:List<Comic>)
}