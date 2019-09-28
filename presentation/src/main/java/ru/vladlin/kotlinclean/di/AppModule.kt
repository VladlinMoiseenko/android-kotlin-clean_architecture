package ru.vladlin.kotlinclean.di

import androidx.room.Room
import ru.vladlin.data.api.Api
import ru.vladlin.data.db.Database
import ru.vladlin.data.entities.NewsDataEntityMapper
import ru.vladlin.data.entities.NewsEntityDataMapper
import ru.vladlin.data.repository.NewsCacheImpl
import ru.vladlin.domain.usecases.GetNewsBaseUseCase
import ru.vladlin.data.repository.NewsRemoteImpl
import ru.vladlin.data.repository.RepositoryNewsImpl
import ru.vladlin.domain.repositories.RepositoryNews
import ru.vladlin.kotlinclean.common.FlowableTransformerAsync
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import ru.vladlin.kotlinclean.news.NewsViewModelBase
import ru.vladlin.kotlinclean.mappers.NewsEntityMapper
import org.koin.android.ext.koin.androidApplication
import retrofit2.Retrofit

val mRepositoryModules = module {
    single(name = "remote") { NewsRemoteImpl(api = get(API))}
    single(name = "local") {
        NewsCacheImpl(database = get(DATABASE), entityToDataMapper = NewsEntityDataMapper(),
                dataToEntityMapper = NewsDataEntityMapper())
    }
    single { RepositoryNewsImpl(remote = get("remote"), cache = get("local")) as RepositoryNews }
}

val mUseCaseModules = module {
    factory(name = "getNewsUseCase") { GetNewsBaseUseCase(transformer = FlowableTransformerAsync(), repositories = get()) }
}

val mNetworkModules = module {
    single(name = RETROFIT_INSTANCE) { createNetworkClient(BASE_URL) }
    single(name = API) { (get(RETROFIT_INSTANCE) as Retrofit).create(Api::class.java) }
}

val mLocalModules = module {
    single(name = DATABASE) { Room.databaseBuilder(androidApplication(), Database::class.java, "articles").build() }
}

val mViewModels = module {
    viewModel {
        NewsViewModelBase(getNewsUseCase = get(GET_NEWS_USE_CASE), mapper = NewsEntityMapper())
    }
}

private const val BASE_URL = "https://newsapi.org/v2/"
private const val RETROFIT_INSTANCE = "Retrofit"
private const val API = "Api"
private const val GET_NEWS_USE_CASE = "getNewsUseCase"
private const val DATABASE = "database"
