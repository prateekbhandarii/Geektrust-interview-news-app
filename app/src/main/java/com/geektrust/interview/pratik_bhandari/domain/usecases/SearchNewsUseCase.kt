package com.geektrust.interview.pratik_bhandari.domain.usecases

import com.geektrust.interview.pratik_bhandari.data.repository.NewsRepository
import com.geektrust.interview.pratik_bhandari.network.NetworkResult
import com.geektrust.interview.pratik_bhandari.ui.presentation.NewsArticleUiData
import com.geektrust.interview.pratik_bhandari.ui.presentation.NewsScreenState
import com.geektrust.interview.pratik_bhandari.ui.presentation.NewsScreenUiData
import javax.inject.Inject

class SearchNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(
        query: String,
        page: Int = 1,
        maxResults: Int = 10,
        language: String? = null,
        country: String? = null
    ): NewsScreenState {
        val result = newsRepository.searchNews(
            query = query,
            page = page,
            maxResults = maxResults,
            language = language,
            country = country
        )

        return when (result) {
            is NetworkResult.Success -> {
                val data = NewsScreenUiData(
                    articles = result.data?.articles?.map {
                        NewsArticleUiData(
                            id = it.id,
                            title = it.title,
                            description = it.description,
                            url = it.url,
                            imageUrl = it.imageUrl,
                            publishedAt = it.publishedAt,
                            sourceName = it.source.name,
                            isRead = it.isRead
                        )
                    }.orEmpty(),
                    totalResults = result.data?.totalArticles ?: 0
                )
                NewsScreenState.Success(data)
            }

            is NetworkResult.Error -> {
                NewsScreenState.Error(result.message ?: "Unknown exception occurred.")
            }
        }
    }
}