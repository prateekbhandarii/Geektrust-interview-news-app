package com.geektrust.interview.pratik_bhandari.data.repository

import com.geektrust.interview.pratik_bhandari.data.mapper.toDomain
import com.geektrust.interview.pratik_bhandari.domain.model.ArticlesPage
import com.geektrust.interview.pratik_bhandari.network.NetworkResult
import com.geektrust.interview.pratik_bhandari.network.RestApi
import com.geektrust.interview.pratik_bhandari.network.safeApiCall
import javax.inject.Inject

interface NewsRepository {

    suspend fun searchNews(
        query: String,
        page: Int,
        maxResults: Int,
        language: String?,
        country: String?
    ): NetworkResult<ArticlesPage>

    suspend fun getTopHeadlines(
        page: Int,
        maxResults: Int,
        category: String?,
        language: String?,
        country: String?
    ): NetworkResult<ArticlesPage>
}

class NewsRepositoryImpl @Inject constructor(
    private val apiService: RestApi
) : NewsRepository {

    override suspend fun searchNews(
        query: String,
        page: Int,
        maxResults: Int,
        language: String?,
        country: String?
    ): NetworkResult<ArticlesPage> {
        return safeApiCall {
            apiService.searchNews(
                query = query,
                language = language,
                country = country,
                maxResults = maxResults,
                page = page
            )
        }.let { result ->
            when (result) {
                is NetworkResult.Success -> {
                    val domainModel = result.data?.toDomain(page, maxResults)
                    if (domainModel != null) {
                        NetworkResult.Success(domainModel)
                    } else {
                        NetworkResult.Error("Failed to parse response")
                    }
                }

                is NetworkResult.Error -> NetworkResult.Error(result.message ?: "Unknown error")
            }
        }
    }

    override suspend fun getTopHeadlines(
        page: Int,
        maxResults: Int,
        category: String?,
        language: String?,
        country: String?
    ): NetworkResult<ArticlesPage> {
        return safeApiCall {
            apiService.getTopHeadlines(
                category = category,
                language = language,
                country = country,
                maxResults = maxResults,
                page = page
            )
        }.let { result ->
            when (result) {
                is NetworkResult.Success -> {
                    val domainModel = result.data?.toDomain(page, maxResults)
                    if (domainModel != null) {
                        NetworkResult.Success(domainModel)
                    } else {
                        NetworkResult.Error("Failed to parse response")
                    }
                }

                is NetworkResult.Error -> NetworkResult.Error(result.message ?: "Unknown error")
            }
        }
    }
}
