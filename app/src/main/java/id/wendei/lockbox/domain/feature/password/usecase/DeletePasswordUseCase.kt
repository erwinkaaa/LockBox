package id.wendei.lockbox.domain.feature.password.usecase

import id.wendei.lockbox.domain.feature.password.model.Password
import id.wendei.lockbox.domain.feature.password.repository.PasswordRepository
import id.wendei.lockbox.domain.utility.DomainResult

class DeletePasswordUseCase(
    private val passwordRepository: PasswordRepository
) {

    suspend operator fun invoke(password: Password): DomainResult<Unit> {
        return passwordRepository.delete(password = password)
    }

}