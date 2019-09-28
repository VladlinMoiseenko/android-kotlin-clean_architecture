package ru.vladlin.data.entities

import com.google.gson.annotations.SerializedName
import ru.vladlin.domain.entities.PublisherEntity
import ru.vladlin.domain.entities.SourcesEntity

data class NewsSourcesData(
    @SerializedName("status") var status: String? = null,
    @SerializedName("articles") var articles: List<PublisherData> = emptyList()
)

class NewsDataEntityMapper constructor() {

    fun mapToEntity(data: NewsSourcesData?): SourcesEntity? = SourcesEntity(
        status = data?.status,
        articles = mapListArticlesToEntity(data?.articles)
    )

    fun mapToEntity(articles: List<PublisherData>?): SourcesEntity? = SourcesEntity(
        articles = mapListArticlesToEntity(articles)
    )

    fun mapListArticlesToEntity(articles: List<PublisherData>?)
            : List<PublisherEntity> = articles?.map { mapArticleToEntity(it) } ?: emptyList()

    fun mapArticleToEntity(response: PublisherData): PublisherEntity = PublisherEntity(
        id = response.id,
        title = response.title,
        description = response.description,
        url = response.url,
        urlToImage = response.urlToImage,
        author = response.author
    )


}


class NewsEntityDataMapper constructor() {

    fun mapToEntity(data: SourcesEntity?): NewsSourcesData? = NewsSourcesData(
        status = data?.status,
        articles = mapListArticlesToEntity(data?.articles)
    )

    fun mapListArticlesToEntity(articles: List<PublisherEntity>?)
            : List<PublisherData> = articles?.map { mapArticleToEntity(it) } ?: emptyList()

    fun mapArticleToEntity(response: PublisherEntity): PublisherData = PublisherData(
        id = response.id,
        title = response.title,
        description = response.description,
        url = response.url,
        urlToImage = response.urlToImage,
        author = response.author
    )


}