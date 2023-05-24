package com.example.geeksforgeeksandroidchallenge.Data

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root
data class Article(
    val title: String,
    val thumbnail: String,
    val pubDate: String
)