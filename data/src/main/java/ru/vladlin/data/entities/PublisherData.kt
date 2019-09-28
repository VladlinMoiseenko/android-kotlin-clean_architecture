package ru.vladlin.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "articles")
data class PublisherData(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @SerializedName("title") var title: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("urlToImage") var urlToImage: String? = null,
    @SerializedName("author") var author: String? = null)