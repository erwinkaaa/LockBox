package id.wendei.lockbox.data.feature.auth

import id.wendei.lockbox.data.feature.auth.source.local.AuthLocalDataSource
import id.wendei.lockbox.domain.feature.auth.repository.AuthRepository
import id.wendei.lockbox.domain.utility.DomainResult

class AuthRepositoryImpl(
    private val localDataSource: AuthLocalDataSource
) : AuthRepository {

    override fun getPin(): DomainResult<String> {
        val pin = localDataSource.getPin().data.orEmpty()
        return DomainResult.Success(data = pin)
    }

    override fun setPin(pin: String): DomainResult<Unit> {
        localDataSource.setPin(pin = pin)
        return DomainResult.Success(data = Unit)
    }

}