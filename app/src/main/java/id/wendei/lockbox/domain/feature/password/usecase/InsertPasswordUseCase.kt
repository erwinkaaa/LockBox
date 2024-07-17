package id.wendei.lockbox.domain.feature.password.usecase

import id.wendei.lockbox.domain.feature.password.model.Password
import id.wendei.lockbox.domain.feature.password.repository.PasswordRepository
import id.wendei.lockbox.domain.utility.DomainResult

class InsertPasswordUseCase(
    private val passwordRepository: PasswordRepository
) {

    suspend operator fun invoke(password: Password): DomainResult<Unit> {
        return passwordRepository.insert(password = password)
    }

}