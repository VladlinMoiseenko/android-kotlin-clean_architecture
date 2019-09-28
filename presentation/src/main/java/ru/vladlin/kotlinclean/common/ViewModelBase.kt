package ru.vladlin.kotlinclean.common

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class ViewModelBase : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    protected fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    private fun clearDisposables() {
        compositeDisposable.clear()
    }

    override fun onCleared() {
        clearDisposables()
    }
}