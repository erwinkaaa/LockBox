package id.wendei.lockbox.data.core.utility

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ErrorBody(
    @SerialName("message")
    val message: String
)
