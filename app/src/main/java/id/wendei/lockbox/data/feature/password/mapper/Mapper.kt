package id.wendei.lockbox.data.feature.password.mapper

import id.wendei.lockbox.PasswordEntity
import id.wendei.lockbox.domain.feature.password.model.Password

fun List<PasswordEntity>?.mapToListPassword(): List<Password> {
    return this?.map { it.toPassword() } ?: emptyList()
}

fun PasswordEntity?.toPassword(): Password {
    return Password(
        id = this?.id ?: 0,
        title = this?.title.orEmpty(),
        credential = this?.credential.orEmpty(),
        password = this?.password.orEmpty()
    )
}