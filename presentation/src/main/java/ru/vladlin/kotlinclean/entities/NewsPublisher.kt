package ru.vladlin.kotlinclean.entities

data class NewsPublisher(
        var id: Int,
        var title: String? = null,
        var description: String? = null,
        var url: String? = null,
        var urlToImage: String? = null,
        var author: String? = null)