package com.geektrust.interview.pratik_bhandari.data.repository

import com.geektrust.interview.pratik_bhandari.data.mapper.toDomain
import com.geektrust.interview.pratik_bhandari.data.source.local.NewsLocalDataSource
import com.geektrust.interview.pratik_bhandari.domain.model.Article
import com.geektrust.interview.pratik_bhandari.domain.model.ArticlesPage
import com.geektrust.interview.pratik_bhandari.network.NetworkResult
import com.geektrust.interview.pratik_bhandari.network.RestApi
import com.geektrust.interview.pratik_bhandari.network.safeApiCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface NewsRepository {

    suspend fun searchNews(
        query: String,
        page: Int,
        maxResults: Int,
        language: String?,
        country: String?
    ): NetworkResult<ArticlesPage>

    suspend fun getTopHeadlines(
        page: Int,
        maxResults: Int,
        category: String?,
        language: String?,
        country: String?
    ): NetworkResult<ArticlesPage>

    // Local data operations
    suspend fun saveArticle(article: Article)
    suspend fun saveArticles(articles: List<Article>)
    suspend fun getArticleById(articleId: String): Article?
    fun getAllArticles(): Flow<List<Article>>
    fun getReadArticles(): Flow<List<Article>>
    fun getUnreadArticles(): Flow<List<Article>>
    suspend fun markArticleAsRead(articleId: String, isRead: Boolean = true)
    suspend fun deleteArticle(articleId: String)
    suspend fun deleteAllArticles()
    fun searchLocalArticles(query: String): Flow<List<Article>>
    suspend fun isArticleRead(articleId: String): Boolean
}

class NewsRepositoryImpl @Inject constructor(
    private val apiService: RestApi,
    private val localDataSource: NewsLocalDataSource
) : NewsRepository {

    override suspend fun searchNews(
        query: String,
        page: Int,
        maxResults: Int,
        language: String?,
        country: String?
    ): NetworkResult<ArticlesPage> {
        return safeApiCall {
            apiService.searchNews(
                query = query,
                language = language,
                country = country,
                maxResults = maxResults,
                page = page
            )
        }.let { result ->
            when (result) {
                is NetworkResult.Success -> {
                    val domainModel = result.data?.toDomain(page, maxResults)
                    if (domainModel != null) {
                        // Save articles to local database for offline access
                        localDataSource.saveArticles(domainModel.articles)
                        NetworkResult.Success(domainModel)
                    } else {
                        NetworkResult.Error("Failed to parse response")
                    }
                }

                is NetworkResult.Error -> NetworkResult.Error(result.message ?: "Unknown error")
            }
        }
    }

    override suspend fun getTopHeadlines(
        page: Int,
        maxResults: Int,
        category: String?,
        language: String?,
        country: String?
    ): NetworkResult<ArticlesPage> {
        return safeApiCall {
            apiService.getTopHeadlines(
                category = category,
                language = language,
                country = country,
                maxResults = maxResults,
                page = page
            )
        }.let { result ->
            when (result) {
                is NetworkResult.Success -> {
                    val domainModel = result.data?.toDomain(page, maxResults)
                    if (domainModel != null) {
                        // Save articles to local database for offline access
                        localDataSource.saveArticles(domainModel.articles)
                        NetworkResult.Success(domainModel)
                    } else {
                        NetworkResult.Error("Failed to parse response")
                    }
                }

                is NetworkResult.Error -> NetworkResult.Error(result.message ?: "Unknown error")
            }
        }
    }

    // Local data operations
    override suspend fun saveArticle(article: Article) {
        localDataSource.saveArticle(article)
    }

    override suspend fun saveArticles(articles: List<Article>) {
        localDataSource.saveArticles(articles)
    }

    override suspend fun getArticleById(articleId: String): Article? {
        return localDataSource.getArticleById(articleId)
    }

    override fun getAllArticles(): Flow<List<Article>> {
        return localDataSource.getAllArticles()
    }

    override fun getReadArticles(): Flow<List<Article>> {
        return localDataSource.getReadArticles()
    }

    override fun getUnreadArticles(): Flow<List<Article>> {
        return localDataSource.getUnreadArticles()
    }

    override suspend fun markArticleAsRead(articleId: String, isRead: Boolean) {
        localDataSource.markArticleAsRead(articleId, isRead)
    }

    override suspend fun deleteArticle(articleId: String) {
        localDataSource.deleteArticle(articleId)
    }

    override suspend fun deleteAllArticles() {
        localDataSource.deleteAllArticles()
    }

    override fun searchLocalArticles(query: String): Flow<List<Article>> {
        return localDataSource.searchArticles(query)
    }

    override suspend fun isArticleRead(articleId: String): Boolean {
        return localDataSource.isArticleRead(articleId)
    }
}
