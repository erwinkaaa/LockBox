package id.wendei.lockbox.data.core.utility

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
sealed class DataResult<T>(
    val data: T? = null,
    @Contextual val exception: Exception? = null,
    val errorBody: ErrorBody? = null
) {
    class Success<T>(data: T) : DataResult<T>(data)
    class Error<T>(
        exception: Exception? = null,
        errorBody: ErrorBody? = null,
        data: T? = null,
    ) : DataResult<T>(data, exception, errorBody)
}