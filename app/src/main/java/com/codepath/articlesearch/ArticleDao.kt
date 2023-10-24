package com.codepath.articlesearch

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
    interface ArticleDao {
        @Query("SELECT * FROM article_table")
        fun getAll(): Flow<List<ArticleEntity>>

        @Insert
        fun insert(articleEntity: ArticleEntity)
        //fun insertAll(articles: List<ArticleEntity>)
        @Query("DELETE FROM article_table")
        fun deleteAll()

     @Query("SELECT SUM(" + "byline" + ") as calorieSum " + " FROM " + "article_table")
     fun getCalorieSum(): Int

    @Query("SELECT COUNT(byline) FROM article_table")
    fun getEntryCount(): Int

}
