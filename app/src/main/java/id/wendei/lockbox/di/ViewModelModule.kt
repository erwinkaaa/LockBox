package id.wendei.lockbox.di

import id.wendei.lockbox.feature.main.MainViewModel
import id.wendei.lockbox.feature.pin.PinViewModel
import id.wendei.lockbox.feature.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::SplashViewModel)
    viewModelOf(::PinViewModel)
    viewModelOf(::MainViewModel)
}