package id.wendei.lockbox.di

import org.koin.dsl.module

val appModule = module {
    includes(
//        localStorageModule,
//        repositoryModule,
//        useCaseModule,
        viewModelModule
    )
}