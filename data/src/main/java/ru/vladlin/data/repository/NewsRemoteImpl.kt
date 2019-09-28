package ru.vladlin.data.repository

import io.reactivex.Flowable
import ru.vladlin.data.api.Api
import ru.vladlin.data.entities.NewsDataEntityMapper
import ru.vladlin.domain.entities.SourcesEntity

class NewsRemoteImpl constructor(private val api:Api): NewsDataStore {

    private val newsMapper =  NewsDataEntityMapper()

    override fun getNews(): Flowable<SourcesEntity> {

        return api.getArticles().map { newsMapper.mapToEntity(it) }
    }

}