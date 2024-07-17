package id.wendei.lockbox.domain.feature.password.model

data class Password(
    val id: Long,
    val title: String,
    val credential: String,
    val password: String
)
