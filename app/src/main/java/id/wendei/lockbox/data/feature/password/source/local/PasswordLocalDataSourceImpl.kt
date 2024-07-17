package id.wendei.lockbox.data.feature.password.source.local

import id.wendei.lockbox.LockBoxDB
import id.wendei.lockbox.PasswordEntity
import id.wendei.lockbox.data.core.utility.DataResult
import id.wendei.lockbox.domain.feature.password.model.Password

class PasswordLocalDataSourceImpl(
    private val lockBoxDB: LockBoxDB
): PasswordLocalDataSource {

    override suspend fun getAll(): DataResult<List<PasswordEntity>> {
        val list = lockBoxDB.passwordQueries.selectAll().executeAsList()
        return DataResult.Success(data = list)
    }

    override suspend fun get(password: Password): DataResult<PasswordEntity> {
        val data = lockBoxDB.passwordQueries.select(id = password.id).executeAsOne()
        return DataResult.Success(data = data)
    }

    override suspend fun insert(password: Password): DataResult<Unit> {
        lockBoxDB.passwordQueries.insert(
            title = password.title,
            credential = password.credential,
            password = password.password
        )
        return DataResult.Success(Unit)
    }

    override suspend fun update(password: Password): DataResult<Unit> {
        lockBoxDB.passwordQueries.update(
            title = password.title,
            credential = password.credential,
            password = password.password
        )
        return DataResult.Success(Unit)
    }

    override suspend fun delete(password: Password): DataResult<Unit> {
        lockBoxDB.passwordQueries.delete(id = password.id)
        return DataResult.Success(Unit)
    }

}