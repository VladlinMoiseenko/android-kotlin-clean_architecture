package ru.vladlin.kotlinclean

import android.app.Application
import org.koin.android.ext.android.startKoin
import ru.vladlin.kotlinclean.di.*

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        loadKoin()
    }

    private fun loadKoin() {
        startKoin(this,
                listOf(mNetworkModules,
                        mViewModels,
                        mRepositoryModules,
                        mUseCaseModules,
                        mLocalModules)

        )
    }
}