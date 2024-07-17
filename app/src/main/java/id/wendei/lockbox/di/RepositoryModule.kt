package id.wendei.lockbox.di

import id.wendei.lockbox.data.feature.password.PasswordRepositoryImpl
import id.wendei.lockbox.domain.feature.password.repository.PasswordRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<PasswordRepository> { PasswordRepositoryImpl(get()) }
}