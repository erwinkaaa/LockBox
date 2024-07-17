package id.wendei.lockbox.di

import id.wendei.lockbox.core.util.PreferenceHelper
import id.wendei.lockbox.data.core.sqldelight.SqlDelight
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localStorageModule = module {
    single { PreferenceHelper.defaultPrefs(androidContext()) }
    single { SqlDelight(androidContext()).provideDriver() }
    single { SqlDelight(androidContext()).provideDatabase(get()) }
}