package id.wendei.lockbox.domain.feature.password.repository

import id.wendei.lockbox.domain.feature.password.model.Password
import id.wendei.lockbox.domain.utility.DomainResult

interface PasswordRepository {
    suspend fun getAll(): DomainResult<List<Password>>
}