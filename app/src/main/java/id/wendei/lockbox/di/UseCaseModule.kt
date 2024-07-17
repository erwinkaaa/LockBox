package id.wendei.lockbox.di

import id.wendei.lockbox.domain.feature.password.usecase.DeletePasswordUseCase
import id.wendei.lockbox.domain.feature.password.usecase.GetAllPasswordUseCase
import id.wendei.lockbox.domain.feature.password.usecase.GetPasswordUseCase
import id.wendei.lockbox.domain.feature.password.usecase.InsertPasswordUseCase
import id.wendei.lockbox.domain.feature.password.usecase.UpdatePasswordUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { GetAllPasswordUseCase(get()) }
    single { GetPasswordUseCase(get()) }
    single { InsertPasswordUseCase(get()) }
    single { UpdatePasswordUseCase(get()) }
    single { DeletePasswordUseCase(get()) }
}