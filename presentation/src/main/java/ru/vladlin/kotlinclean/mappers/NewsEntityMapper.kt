package ru.vladlin.kotlinclean.mappers

import ru.vladlin.domain.common.Mapper
import ru.vladlin.domain.entities.PublisherEntity
import ru.vladlin.domain.entities.SourcesEntity
import ru.vladlin.kotlinclean.entities.NewsPublisher
import ru.vladlin.kotlinclean.entities.NewsSources

class NewsEntityMapper : Mapper<SourcesEntity, NewsSources>() {
    override fun mapFrom(data: SourcesEntity): NewsSources = NewsSources(
        status = data?.status,
        articles = mapListArticlesToPresetation(data?.articles)
    )

    private fun mapListArticlesToPresetation(articles: List<PublisherEntity>?)
            : List<NewsPublisher> = articles?.map { mapArticleToPresentation(it) }
            ?: emptyList()

    private fun mapArticleToPresentation(response: PublisherEntity): NewsPublisher = NewsPublisher(
            id = response.id,
        title = response.title,
            description = response.description,
            url = response.url,
            urlToImage = response.urlToImage,
            author = response.author
    )

}