package id.wendei.lockbox.data.feature.password

import id.wendei.lockbox.data.core.utility.DataResult
import id.wendei.lockbox.data.feature.password.mapper.mapToListPassword
import id.wendei.lockbox.data.feature.password.mapper.toPassword
import id.wendei.lockbox.data.feature.password.source.local.PasswordLocalDataSource
import id.wendei.lockbox.domain.feature.password.model.Password
import id.wendei.lockbox.domain.feature.password.repository.PasswordRepository
import id.wendei.lockbox.domain.utility.DomainResult

class PasswordRepositoryImpl(
    private val localDataSource: PasswordLocalDataSource
) : PasswordRepository {

    override suspend fun getAll(): DomainResult<List<Password>> {
        return when (val result = localDataSource.getAll()) {
            is DataResult.Success -> DomainResult.Success(data = result.data.mapToListPassword())
            is DataResult.Error -> DomainResult.Error(message = result.errorBody?.message.orEmpty())
        }
    }

    override suspend fun get(password: Password): DomainResult<Password> {
        return when (val result = localDataSource.get(password = password)) {
            is DataResult.Success -> DomainResult.Success(data = result.data.toPassword())
            is DataResult.Error -> DomainResult.Error(message = result.errorBody?.message.orEmpty())
        }
    }

    override suspend fun insert(password: Password): DomainResult<Unit> {
        return when (val result = localDataSource.insert(password = password)) {
            is DataResult.Success -> DomainResult.Success(data = Unit)
            is DataResult.Error -> DomainResult.Error(message = result.errorBody?.message.orEmpty())
        }
    }

    override suspend fun update(password: Password): DomainResult<Unit> {
        return when (val result = localDataSource.update(password = password)) {
            is DataResult.Success -> DomainResult.Success(data = Unit)
            is DataResult.Error -> DomainResult.Error(message = result.errorBody?.message.orEmpty())
        }
    }

    override suspend fun delete(password: Password): DomainResult<Unit> {
        return when (val result = localDataSource.delete(password = password)) {
            is DataResult.Success -> DomainResult.Success(data = Unit)
            is DataResult.Error -> DomainResult.Error(message = result.errorBody?.message.orEmpty())
        }
    }

}