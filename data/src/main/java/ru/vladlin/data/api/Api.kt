package ru.vladlin.data.api

import io.reactivex.Flowable
import retrofit2.http.GET
import ru.vladlin.data.entities.NewsSourcesData

interface Api {

    @GET("top-headlines?country=us")
    fun getArticles(): Flowable<NewsSourcesData>

}