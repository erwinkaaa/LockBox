package id.wendei.lockbox.domain.feature.auth.usecase

import id.wendei.lockbox.domain.feature.auth.repository.AuthRepository
import id.wendei.lockbox.domain.utility.DomainResult

class GetPinUseCase(
    private val authRepository: AuthRepository
) {

    operator fun invoke(): DomainResult<String> {
        return authRepository.getPin()
    }

}