package ru.vladlin.data.repository

import io.reactivex.Flowable
import ru.vladlin.data.db.Dao
import ru.vladlin.data.db.Database
import ru.vladlin.data.entities.NewsDataEntityMapper
import ru.vladlin.data.entities.NewsEntityDataMapper
import ru.vladlin.domain.entities.SourcesEntity

class NewsCacheImpl(private val database: Database,
                    private val entityToDataMapper: NewsEntityDataMapper,
                    private val dataToEntityMapper: NewsDataEntityMapper
) : NewsDataStore {

    private val dao: Dao = database.getArticlesDao()

    override fun getNews(): Flowable<SourcesEntity> {
        return dao.getAllArticles().map { it ->
            dataToEntityMapper.mapToEntity(it)
        }
    }

    fun saveArticles(it: SourcesEntity) {
        dao.clear()
        dao.saveAllArticles(it.articles.map { articles -> entityToDataMapper.mapArticleToEntity(articles) })
    }

}