package com.geektrust.interview.pratik_bhandari.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.geektrust.interview.pratik_bhandari.data.source.local.dao.ArticleDao
import com.geektrust.interview.pratik_bhandari.data.source.local.entity.ArticleEntity

@Database(
    entities = [ArticleEntity::class],
    version = 1,
    exportSchema = false
)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao

    companion object {
        const val DATABASE_NAME = "news_database"
    }
}
