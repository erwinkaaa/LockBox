package id.wendei.lockbox.data.feature.password

import id.wendei.lockbox.data.core.utility.DataResult
import id.wendei.lockbox.data.feature.password.mapper.mapToListPassword
import id.wendei.lockbox.data.feature.password.source.local.PasswordLocalDataSource
import id.wendei.lockbox.domain.feature.password.model.Password
import id.wendei.lockbox.domain.feature.password.repository.PasswordRepository
import id.wendei.lockbox.domain.utility.DomainResult

class PasswordRepositoryImpl(
    private val localDataSource: PasswordLocalDataSource
): PasswordRepository {

    override suspend fun getAll(): DomainResult<List<Password>> {
        return when (val result = localDataSource.getAll()) {
            is DataResult.Success -> DomainResult.Success(data = result.data.mapToListPassword())
            is DataResult.Error -> DomainResult.Error(message = result.errorBody?.message.orEmpty())
        }
    }

}