package id.wendei.lockbox.di

import id.wendei.lockbox.data.feature.auth.source.local.AuthLocalDataSource
import id.wendei.lockbox.data.feature.auth.source.local.AuthLocalDataSourceImpl
import id.wendei.lockbox.data.feature.password.source.local.PasswordLocalDataSource
import id.wendei.lockbox.data.feature.password.source.local.PasswordLocalDataSourceImpl
import org.koin.dsl.module

val localDataSourceModule = module {
    single<AuthLocalDataSource> { AuthLocalDataSourceImpl(get()) }
    single<PasswordLocalDataSource> { PasswordLocalDataSourceImpl(get()) }
}