package com.example.geeksforgeeksandroidchallenge.Network

import com.example.geeksforgeeksandroidchallenge.Data.Article
import com.example.geeksforgeeksandroidchallenge.Data.News
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import java.net.NetworkInterface


private const val BASE_URL = "https://api.rss2json.com/"
private const val RSS_URL = "http://www.abc.net.au/news/feed/51120/rss.xml"
private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

interface NewsInterface {
    @GET("v1/api.json?rss_url=$RSS_URL")
    suspend fun getNews(): Response<News>
}

object NewsService {
    val newsInstance: NewsInterface

    init {
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi)).build()
        newsInstance = retrofit.create(NewsInterface::class.java)
    }
}