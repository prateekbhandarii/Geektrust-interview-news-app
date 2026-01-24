package com.geektrust.interview.pratik_bhandari.ui.presentation

sealed interface NewsScreenState {
    object Loading : NewsScreenState
    data class Success(val data: NewsScreenUiData) : NewsScreenState
    data class Error(val message: String) : NewsScreenState
}

data class NewsScreenUiData(
    val articles: List<NewsArticleUiData>,
    val totalResults: Int
)

data class NewsArticleUiData(
    val id: String,
    val title: String,
    val description: String?,
    val url: String,
    val imageUrl: String?,
    val publishedAt: String,
    val sourceName: String
)