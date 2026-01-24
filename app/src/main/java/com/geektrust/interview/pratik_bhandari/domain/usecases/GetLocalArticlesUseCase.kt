package com.geektrust.interview.pratik_bhandari.domain.usecases

import com.geektrust.interview.pratik_bhandari.data.repository.NewsRepository
import com.geektrust.interview.pratik_bhandari.domain.model.Article
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocalArticlesUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    operator fun invoke(): Flow<List<Article>> {
        return repository.getAllArticles()
    }
}
