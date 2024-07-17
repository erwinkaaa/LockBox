package id.wendei.lockbox.domain.feature.password.repository

import id.wendei.lockbox.domain.feature.password.model.Password
import id.wendei.lockbox.domain.utility.DomainResult

interface PasswordRepository {
    suspend fun getAll(): DomainResult<List<Password>>
    suspend fun get(password: Password): DomainResult<Password>
    suspend fun insert(password: Password): DomainResult<Unit>
    suspend fun update(password: Password): DomainResult<Unit>
    suspend fun delete(password: Password): DomainResult<Unit>
}