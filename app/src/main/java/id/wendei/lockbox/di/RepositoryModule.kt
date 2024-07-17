package id.wendei.lockbox.di

import id.wendei.lockbox.data.feature.auth.AuthRepositoryImpl
import id.wendei.lockbox.data.feature.password.PasswordRepositoryImpl
import id.wendei.lockbox.domain.feature.auth.repository.AuthRepository
import id.wendei.lockbox.domain.feature.password.repository.PasswordRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get()) }
    single<PasswordRepository> { PasswordRepositoryImpl(get()) }
}