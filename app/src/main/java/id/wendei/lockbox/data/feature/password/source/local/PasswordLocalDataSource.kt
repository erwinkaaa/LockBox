package id.wendei.lockbox.data.feature.password.source.local

import id.wendei.lockbox.PasswordEntity
import id.wendei.lockbox.data.core.utility.DataResult

interface PasswordLocalDataSource {
    suspend fun getAll(): DataResult<List<PasswordEntity>>
}