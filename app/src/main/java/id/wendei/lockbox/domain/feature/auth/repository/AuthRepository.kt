package id.wendei.lockbox.domain.feature.auth.repository

import id.wendei.lockbox.domain.utility.DomainResult

interface AuthRepository {
    fun getPin(): DomainResult<String>
    fun setPin(pin: String): DomainResult<Unit>
}