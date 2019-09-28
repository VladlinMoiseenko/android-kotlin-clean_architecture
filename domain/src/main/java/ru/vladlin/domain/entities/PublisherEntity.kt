package ru.vladlin.domain.entities

data class PublisherEntity(
        var id: Int,
        var title: String? = null,
        var description: String? = null,
        var url: String? = null,
        var urlToImage: String? = null,
        var author: String? = null)