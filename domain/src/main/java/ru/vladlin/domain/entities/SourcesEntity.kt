package ru.vladlin.domain.entities

data class SourcesEntity(
        var status: String? = null,
        var articles: List<PublisherEntity> = emptyList()
)