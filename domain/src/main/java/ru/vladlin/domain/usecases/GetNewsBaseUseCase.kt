package ru.vladlin.domain.usecases

import ru.vladlin.domain.common.FlowableBaseUseCase
import ru.vladlin.domain.common.FlowableRxTransformer
import ru.vladlin.domain.entities.SourcesEntity
import ru.vladlin.domain.repositories.RepositoryNews
import io.reactivex.Flowable

class GetNewsBaseUseCase(private val transformer: FlowableRxTransformer<SourcesEntity>,
                         private val repositories: RepositoryNews): FlowableBaseUseCase<SourcesEntity>(transformer){

    override fun createFlowable(data: Map<String, Any>?): Flowable<SourcesEntity> {
        return repositories.getNews()
    }

    fun getNews(): Flowable<SourcesEntity>{
        val data = HashMap<String, String>()
        return single(data)
    }
}