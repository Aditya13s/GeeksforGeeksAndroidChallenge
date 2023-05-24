package com.example.geeksforgeeksandroidchallenge.Network

import com.example.geeksforgeeksandroidchallenge.Data.Article
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private const val BASE_URL = "https://api.rss2json.com/v1/"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


interface ApiService {
    @GET("api.json?rss_url=http://www.abc.net.au/news/feed/51120")
    suspend fun getThumbnail(): List<Article>

    @GET("api.json?rss_url=http://www.abc.net.au/news/feed/51120")
    suspend fun getTitle(): List<Article>

    @GET("api.json?rss_url=http://www.abc.net.au/news/feed/51120")
    suspend fun getPubdate(): List<Article>
}
object Api{
    val retrofitService : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}