package com.example.comicreaderapp.Interface

import com.example.comicreaderapp.models.Comic

interface IComicLoadDoneListener {
    fun OnComicLoadDoneListener(comicList:List<Comic>)
}