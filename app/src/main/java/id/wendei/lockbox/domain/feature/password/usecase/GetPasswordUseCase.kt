package id.wendei.lockbox.domain.feature.password.usecase

import id.wendei.lockbox.domain.feature.password.model.Password
import id.wendei.lockbox.domain.feature.password.repository.PasswordRepository
import id.wendei.lockbox.domain.utility.DomainResult

class GetPasswordUseCase(
    private val passwordRepository: PasswordRepository
) {

    suspend operator fun invoke(password: Password): DomainResult<Password> {
        return passwordRepository.get(password = password)
    }

}