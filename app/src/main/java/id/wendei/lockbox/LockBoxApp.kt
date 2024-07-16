package id.wendei.lockbox

import android.app.Application
import id.wendei.lockbox.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class LockBoxApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@LockBoxApp)
            modules(appModule)
        }
    }

}