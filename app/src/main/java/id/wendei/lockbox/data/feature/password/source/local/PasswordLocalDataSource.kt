package id.wendei.lockbox.data.feature.password.source.local

import id.wendei.lockbox.PasswordEntity
import id.wendei.lockbox.data.core.utility.DataResult
import id.wendei.lockbox.domain.feature.password.model.Password

interface PasswordLocalDataSource {
    suspend fun getAll(): DataResult<List<PasswordEntity>>
    suspend fun get(password: Password): DataResult<PasswordEntity>
    suspend fun insert(password: Password): DataResult<Unit>
    suspend fun update(password: Password): DataResult<Unit>
    suspend fun delete(password: Password): DataResult<Unit>
}