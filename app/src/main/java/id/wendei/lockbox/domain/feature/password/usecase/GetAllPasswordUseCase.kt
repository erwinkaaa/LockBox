package id.wendei.lockbox.domain.feature.password.usecase

import id.wendei.lockbox.domain.feature.password.model.Password
import id.wendei.lockbox.domain.feature.password.repository.PasswordRepository
import id.wendei.lockbox.domain.utility.DomainResult

class GetAllPasswordUseCase(
    private val passwordRepository: PasswordRepository
) {

    suspend operator fun invoke(): DomainResult<List<Password>> {
        return passwordRepository.getAll()
    }

}