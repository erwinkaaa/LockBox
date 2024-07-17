package id.wendei.lockbox.data.feature.auth.source.local

import android.content.SharedPreferences
import id.wendei.lockbox.core.util.PreferenceHelper.getPin
import id.wendei.lockbox.core.util.PreferenceHelper.setPin
import id.wendei.lockbox.data.core.utility.DataResult

class AuthLocalDataSourceImpl(
    private val sharedPreferences: SharedPreferences
): AuthLocalDataSource {

    override fun getPin(): DataResult<String> {
        val pin = sharedPreferences.getPin()
        return DataResult.Success(data = pin)
    }

    override fun setPin(pin: String): DataResult<Unit> {
        sharedPreferences.setPin(pin = pin)
        return DataResult.Success(Unit)
    }

}