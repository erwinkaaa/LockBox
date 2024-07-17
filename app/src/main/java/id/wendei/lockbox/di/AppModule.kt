package id.wendei.lockbox.di

import org.koin.dsl.module

val appModule = module {
    includes(
        localStorageModule,
        localDataSourceModule,
        repositoryModule,
        useCaseModule,
        viewModelModule
    )
}