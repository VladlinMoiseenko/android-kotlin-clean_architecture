package ru.vladlin.data.repository

import io.reactivex.Flowable
import ru.vladlin.domain.entities.SourcesEntity


interface NewsDataStore{
    fun getNews(): Flowable<SourcesEntity>
}