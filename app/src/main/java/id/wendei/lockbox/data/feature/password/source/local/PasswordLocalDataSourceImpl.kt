package id.wendei.lockbox.data.feature.password.source.local

import id.wendei.lockbox.LockBoxDB
import id.wendei.lockbox.PasswordEntity
import id.wendei.lockbox.data.core.utility.DataResult

class PasswordLocalDataSourceImpl(
    private val lockBoxDB: LockBoxDB
): PasswordLocalDataSource {

    override suspend fun getAll(): DataResult<List<PasswordEntity>> {
        val list = lockBoxDB.passwordQueries.selectAll().executeAsList()
        return DataResult.Success(data = list)
    }

}