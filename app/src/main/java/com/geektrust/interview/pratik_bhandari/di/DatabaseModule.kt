package com.geektrust.interview.pratik_bhandari.di

import android.content.Context
import androidx.room.Room
import com.geektrust.interview.pratik_bhandari.data.source.local.NewsLocalDataSource
import com.geektrust.interview.pratik_bhandari.data.source.local.NewsLocalDataSourceImpl
import com.geektrust.interview.pratik_bhandari.data.source.local.dao.ArticleDao
import com.geektrust.interview.pratik_bhandari.data.source.local.database.NewsDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideNewsDatabase(@ApplicationContext context: Context): NewsDatabase {
        return Room.databaseBuilder(
            context,
            NewsDatabase::class.java,
            NewsDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideArticleDao(database: NewsDatabase): ArticleDao {
        return database.articleDao()
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {

    @Binds
    @Singleton
    abstract fun bindNewsLocalDataSource(
        implementation: NewsLocalDataSourceImpl
    ): NewsLocalDataSource
}
