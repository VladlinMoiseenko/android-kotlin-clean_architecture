package ru.vladlin.data.repository

import io.reactivex.Flowable
import ru.vladlin.domain.entities.SourcesEntity
import ru.vladlin.domain.repositories.RepositoryNews

class RepositoryNewsImpl(private val remote: NewsRemoteImpl,
                         private val cache: NewsCacheImpl) : RepositoryNews {

    override fun getLocalNews(): Flowable<SourcesEntity> {
        return cache.getNews()
    }

    override fun getRemoteNews(): Flowable<SourcesEntity> {
        return remote.getNews()
    }

    override fun getNews(): Flowable<SourcesEntity> {
        val updateNewsFlowable = remote.getNews()
        return cache.getNews()
                .mergeWith(updateNewsFlowable.doOnNext{
                    remoteNews -> cache.saveArticles(remoteNews)
                })
    }
}