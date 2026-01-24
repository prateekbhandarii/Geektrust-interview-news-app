package com.geektrust.interview.pratik_bhandari.domain.usecases

import com.geektrust.interview.pratik_bhandari.data.repository.NewsRepository
import com.geektrust.interview.pratik_bhandari.network.NetworkResult
import com.geektrust.interview.pratik_bhandari.ui.presentation.NewsScreenState
import com.geektrust.interview.pratik_bhandari.ui.presentation.NewsScreenUiData
import javax.inject.Inject

class GetTopNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(
        page: Int = 1,
        maxResults: Int = 10,
        category: String? = null,
        language: String? = null,
        country: String? = null
    ): NewsScreenState {
        val response = newsRepository.getTopHeadlines(
            page = page,
            maxResults = maxResults,
            category = category,
            language = language,
            country = country
        )

        return when (response) {
            is NetworkResult.Error -> {
                NewsScreenState.Error(response.message ?: "Unknown exception occurred.")
            }

            is NetworkResult.Success -> {
                val data =
                    NewsScreenUiData(
                        articles = response.data?.articles?.map { article ->
                            com.geektrust.interview.pratik_bhandari.ui.presentation.NewsArticleUiData(
                                id = article.id,
                                title = article.title,
                                description = article.description,
                                url = article.url,
                                imageUrl = article.imageUrl,
                                publishedAt = article.publishedAt,
                                sourceName = article.source.name,
                                isRead = article.isRead
                            )
                        }.orEmpty(),
                        totalResults = response.data?.totalArticles ?: 0
                    )

                NewsScreenState.Success(data)
            }
        }
    }
}