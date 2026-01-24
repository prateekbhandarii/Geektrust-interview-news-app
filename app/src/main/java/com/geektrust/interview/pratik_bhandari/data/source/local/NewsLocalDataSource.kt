package com.geektrust.interview.pratik_bhandari.data.source.local

import com.geektrust.interview.pratik_bhandari.data.source.local.dao.ArticleDao
import com.geektrust.interview.pratik_bhandari.data.source.local.entity.ArticleEntity
import com.geektrust.interview.pratik_bhandari.data.source.local.mapper.toDomain
import com.geektrust.interview.pratik_bhandari.data.source.local.mapper.toEntity
import com.geektrust.interview.pratik_bhandari.domain.model.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface NewsLocalDataSource {
    suspend fun saveArticle(article: Article)
    suspend fun saveArticles(articles: List<Article>)
    suspend fun getArticleById(articleId: String): Article?
    fun getAllArticles(): Flow<List<Article>>
    fun getReadArticles(): Flow<List<Article>>
    fun getUnreadArticles(): Flow<List<Article>>
    suspend fun markArticleAsRead(articleId: String, isRead: Boolean = true)
    suspend fun deleteArticle(articleId: String)
    suspend fun deleteAllArticles()
    fun searchArticles(query: String): Flow<List<Article>>
    suspend fun isArticleRead(articleId: String): Boolean
}

class NewsLocalDataSourceImpl @Inject constructor(
    private val articleDao: ArticleDao
) : NewsLocalDataSource {

    override suspend fun saveArticle(article: Article) {
        val existingArticle = articleDao.getArticleById(article.id)
        val isRead = existingArticle?.isRead ?: false
        articleDao.insertArticle(article.toEntity(isRead))
    }

    override suspend fun saveArticles(articles: List<Article>) {
        val entities = articles.map { article ->
            val existingArticle = articleDao.getArticleById(article.id)
            val isRead = existingArticle?.isRead ?: false
            article.toEntity(isRead)
        }
        articleDao.insertArticles(entities)
    }

    override suspend fun getArticleById(articleId: String): Article? {
        return articleDao.getArticleById(articleId)?.toDomain()
    }

    override fun getAllArticles(): Flow<List<Article>> {
        return articleDao.getAllArticles().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getReadArticles(): Flow<List<Article>> {
        return articleDao.getArticlesByReadStatus(true).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getUnreadArticles(): Flow<List<Article>> {
        return articleDao.getArticlesByReadStatus(false).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun markArticleAsRead(articleId: String, isRead: Boolean) {
        articleDao.markArticleAsRead(articleId, isRead)
    }

    override suspend fun deleteArticle(articleId: String) {
        articleDao.deleteArticle(articleId)
    }

    override suspend fun deleteAllArticles() {
        articleDao.deleteAllArticles()
    }

    override fun searchArticles(query: String): Flow<List<Article>> {
        return articleDao.searchArticles(query).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun isArticleRead(articleId: String): Boolean {
        return articleDao.getArticleById(articleId)?.isRead ?: false
    }
}
