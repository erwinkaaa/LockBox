package id.wendei.lockbox.data.feature.password.mapper

import id.wendei.lockbox.PasswordEntity
import id.wendei.lockbox.domain.feature.password.model.Password

fun List<PasswordEntity>?.mapToListPassword(): List<Password> {
    return this?.map { it.toPassword() } ?: emptyList()
}

private fun PasswordEntity.toPassword(): Password {
    return Password(
        id = id,
        title = title,
        credential = credential,
        password = password
    )
}