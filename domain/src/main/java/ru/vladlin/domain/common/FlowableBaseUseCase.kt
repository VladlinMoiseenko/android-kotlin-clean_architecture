package ru.vladlin.domain.common

import io.reactivex.Flowable

abstract class FlowableBaseUseCase<T>(private val transformer: FlowableRxTransformer<T>) {

    abstract fun createFlowable(data: Map<String, Any>? = null): Flowable<T>

    fun single(withData: Map<String, Any>? = null): Flowable<T> {
        return createFlowable(withData).compose(transformer)
    }
}