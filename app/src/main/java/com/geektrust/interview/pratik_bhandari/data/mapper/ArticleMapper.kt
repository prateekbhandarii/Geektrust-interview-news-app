package com.geektrust.interview.pratik_bhandari.data.mapper

import com.geektrust.interview.pratik_bhandari.data.source.dto.ArticleResponse
import com.geektrust.interview.pratik_bhandari.data.source.dto.NewsResponse
import com.geektrust.interview.pratik_bhandari.data.source.dto.SourceResponse
import com.geektrust.interview.pratik_bhandari.domain.model.Article
import com.geektrust.interview.pratik_bhandari.domain.model.ArticlesPage
import com.geektrust.interview.pratik_bhandari.domain.model.Source

fun ArticleResponse.toDomain(): Article {
    return Article(
        id = id,
        title = title,
        description = description,
        content = content,
        url = url,
        imageUrl = image,
        publishedAt = publishedAt,
        language = lang,
        source = source.toDomain()
    )
}

fun SourceResponse.toDomain(): Source {
    return Source(
        id = id,
        name = name,
        url = url
    )
}

fun NewsResponse.toDomain(currentPage: Int, pageSize: Int): ArticlesPage {
    val articlesList = articles.map { it.toDomain() }
    val hasNextPage = (currentPage * pageSize) < totalArticles
    val infoMessage = information?.realTimeArticles?.message

    return ArticlesPage(
        articles = articlesList,
        totalArticles = totalArticles,
        currentPage = currentPage,
        pageSize = pageSize,
        hasNextPage = hasNextPage,
        informationMessage = infoMessage
    )
}
