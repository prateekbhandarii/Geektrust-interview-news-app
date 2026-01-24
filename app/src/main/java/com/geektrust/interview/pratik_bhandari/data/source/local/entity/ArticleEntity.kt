package com.geektrust.interview.pratik_bhandari.data.source.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles")
data class ArticleEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val description: String,
    val content: String,
    val url: String,
    val imageUrl: String,
    val publishedAt: String,
    val language: String,
    @Embedded(prefix = "source_")
    val source: SourceEntity,
    val isRead: Boolean = false,
    val savedAt: Long = System.currentTimeMillis()
)
