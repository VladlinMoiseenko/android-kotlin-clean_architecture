package ru.vladlin.kotlinclean.news

import androidx.lifecycle.MutableLiveData
import android.util.Log
import ru.vladlin.domain.common.Mapper
import ru.vladlin.domain.entities.SourcesEntity
import ru.vladlin.domain.usecases.GetNewsBaseUseCase
import ru.vladlin.kotlinclean.common.ViewModelBase
import ru.vladlin.kotlinclean.entities.Data
import ru.vladlin.kotlinclean.entities.Error
import ru.vladlin.kotlinclean.entities.NewsSources
import ru.vladlin.kotlinclean.entities.Status

class NewsViewModelBase(private val getNewsUseCase: GetNewsBaseUseCase,
                        private val mapper: Mapper<SourcesEntity, NewsSources>) : ViewModelBase() {

    companion object {
        private val TAG = "viewmodel"
    }

    var mNews = MutableLiveData<Data<NewsSources>>()

    fun fetchNews() {
        val disposable = getNewsUseCase.getNews()
                .flatMap { mapper.Flowable(it) }
                .subscribe({ response ->
                    Log.d(TAG, "On Next Called")
                    mNews.value = Data(responseType = Status.SUCCESSFUL, data = response)
                }, { error ->
                    Log.d(TAG, "On Error Called")
                    mNews.value = Data(responseType = Status.ERROR, error = Error(error.message))
                }, {
                    Log.d(TAG, "On Complete Called")
                })

        addDisposable(disposable)
    }

    fun getNewsLiveData() = mNews
}