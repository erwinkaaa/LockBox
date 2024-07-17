package id.wendei.lockbox.di

import id.wendei.lockbox.domain.feature.password.usecase.GetAllPasswordUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { GetAllPasswordUseCase(get()) }
}