package id.wendei.lockbox.data.feature.auth.source.local

import id.wendei.lockbox.data.core.utility.DataResult

interface AuthLocalDataSource {
    fun getPin(): DataResult<String>
    fun setPin(pin: String): DataResult<Unit>
}