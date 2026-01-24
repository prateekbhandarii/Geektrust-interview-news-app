package com.geektrust.interview.pratik_bhandari.data.source.remote

import com.geektrust.interview.pratik_bhandari.data.source.dto.NewsResponse
import com.geektrust.interview.pratik_bhandari.network.RestApi
import retrofit2.Response
import javax.inject.Inject

interface NewsRemoteDataSource {
    suspend fun getNews(
        category: String,
        country: String,
        pageSize: Int,
        page: Int
    ): Response<NewsResponse>

    suspend fun searchNews(
        query: String,
        pageSize: Int,
        page: Int
    ): Response<NewsResponse>
}

class NewsRemoteDataSourceImpl @Inject constructor(
    private val newsApiService: RestApi
) : NewsRemoteDataSource {
    override suspend fun getNews(
        category: String,
        country: String,
        pageSize: Int,
        page: Int
    ): Response<NewsResponse> {
        return newsApiService.getTopHeadlines(
            category = category,
            country = country,
            language = "en",
            maxResults = pageSize,
            page = page
        )
    }

    override suspend fun searchNews(
        query: String,
        pageSize: Int,
        page: Int
    ): Response<NewsResponse> {
        return newsApiService.searchNews(
            query = query,
            language = "en",
            maxResults = pageSize,
            page = page
        )
    }
}