package ru.vladlin.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.vladlin.data.entities.PublisherData

@Database(entities = arrayOf(PublisherData::class), version = 1)
abstract class Database : RoomDatabase() {
    abstract fun getArticlesDao(): Dao
}