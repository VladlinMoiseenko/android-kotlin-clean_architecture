package ru.vladlin.domain.repositories

import ru.vladlin.domain.entities.SourcesEntity
import io.reactivex.Flowable

interface RepositoryNews {

    fun getNews(): Flowable<SourcesEntity>
    fun getLocalNews(): Flowable<SourcesEntity>
    fun getRemoteNews(): Flowable<SourcesEntity>

}