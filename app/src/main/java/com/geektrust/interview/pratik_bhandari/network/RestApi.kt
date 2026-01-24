package com.geektrust.interview.pratik_bhandari.network

import com.geektrust.interview.pratik_bhandari.data.source.dto.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApi {

    @GET("v4/search")
    suspend fun searchNews(
        @Query("q") query: String,
        @Query("lang") language: String? = null,
        @Query("country") country: String? = null,
        @Query("max") maxResults: Int? = null,
        @Query("page") page: Int? = null,
        @Query("from") from: String? = null,
        @Query("to") to: String? = null,
        @Query("sortby") sortBy: String? = null
    ): Response<NewsResponse>

    @GET("v4/top-headlines")
    suspend fun getTopHeadlines(
        @Query("category") category: String? = null,
        @Query("lang") language: String? = null,
        @Query("country") country: String? = null,
        @Query("max") maxResults: Int? = null,
        @Query("page") page: Int? = null
    ): Response<NewsResponse>
}
