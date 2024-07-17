package id.wendei.lockbox.di

import id.wendei.lockbox.data.feature.password.source.local.PasswordLocalDataSource
import id.wendei.lockbox.data.feature.password.source.local.PasswordLocalDataSourceImpl
import org.koin.dsl.module

val localDataSourceModule = module {
    single<PasswordLocalDataSource> { PasswordLocalDataSourceImpl(get()) }
}