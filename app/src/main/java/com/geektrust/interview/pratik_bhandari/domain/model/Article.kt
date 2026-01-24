package com.geektrust.interview.pratik_bhandari.domain.model

data class Article(
    val id: String,
    val title: String,
    val description: String,
    val content: String,
    val url: String,
    val imageUrl: String,
    val publishedAt: String,
    val language: String,
    val source: Source
)
