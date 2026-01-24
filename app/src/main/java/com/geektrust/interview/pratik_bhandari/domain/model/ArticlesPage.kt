package com.geektrust.interview.pratik_bhandari.domain.model

data class ArticlesPage(
    val articles: List<Article>,
    val totalArticles: Int,
    val currentPage: Int,
    val pageSize: Int,
    val hasNextPage: Boolean,
    val informationMessage: String? = null
) {
    val totalPages: Int
        get() = (totalArticles + pageSize - 1) / pageSize
}
