package ru.vladlin.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Flowable
import ru.vladlin.data.entities.PublisherData

@Dao
interface Dao{

    @Query("DELETE FROM articles")
    fun clear()

    @Query("Select * from articles")
    fun getAllArticles(): Flowable<List<PublisherData>?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllArticles(articles: List<PublisherData>)

}