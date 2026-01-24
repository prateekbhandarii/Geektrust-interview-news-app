package com.geektrust.interview.pratik_bhandari.domain.usecases

import com.geektrust.interview.pratik_bhandari.data.repository.NewsRepository
import javax.inject.Inject

class MarkArticleAsReadUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(articleId: String, isRead: Boolean = true) {
        repository.markArticleAsRead(articleId, isRead)
    }
}
