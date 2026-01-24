package com.geektrust.interview.pratik_bhandari.data.source.local.mapper

import com.geektrust.interview.pratik_bhandari.data.source.local.entity.ArticleEntity
import com.geektrust.interview.pratik_bhandari.data.source.local.entity.SourceEntity
import com.geektrust.interview.pratik_bhandari.domain.model.Article
import com.geektrust.interview.pratik_bhandari.domain.model.Source

fun Article.toEntity(isRead: Boolean = false): ArticleEntity {
    return ArticleEntity(
        id = id,
        title = title,
        description = description,
        content = content,
        url = url,
        imageUrl = imageUrl,
        publishedAt = publishedAt,
        language = language,
        source = source.toEntity(),
        isRead = isRead
    )
}

fun Source.toEntity(): SourceEntity {
    return SourceEntity(
        id = id,
        name = name,
        url = url
    )
}

fun ArticleEntity.toDomain(): Article {
    return Article(
        id = id,
        title = title,
        description = description,
        content = content,
        url = url,
        imageUrl = imageUrl,
        publishedAt = publishedAt,
        language = language,
        source = source.toDomain(),
        isRead = isRead
    )
}

fun SourceEntity.toDomain(): Source {
    return Source(
        id = id,
        name = name,
        url = url
    )
}
