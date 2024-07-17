package id.wendei.lockbox.data.core.sqldelight

import android.content.Context
import app.cash.sqldelight.Query
import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import id.wendei.lockbox.LockBoxDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class SqlDelight(context: Context) {

    private val dbName = "${context.packageName}.db"

    private val driver: SqlDriver = AndroidSqliteDriver(
        schema = LockBoxDB.Schema,
        context = context,
        name = dbName
    )

    init {
        LockBoxDB.Schema.create(driver = driver)
    }

    fun provideDriver(): SqlDriver {
        return driver
    }

    fun provideDatabase(driver: SqlDriver): LockBoxDB {
        return LockBoxDB(driver = driver)
    }

}

fun <T : Any> Query<T>.withFlow(): Flow<List<T>> {
    return this.asFlow().mapToList(Dispatchers.IO)
}