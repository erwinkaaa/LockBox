package id.wendei.lockbox.domain.feature.auth.usecase

import id.wendei.lockbox.domain.feature.auth.repository.AuthRepository
import id.wendei.lockbox.domain.utility.DomainResult

class SetPinUseCase(
    private val authRepository: AuthRepository
) {

    operator fun invoke(pin: String): DomainResult<Unit> {
        return authRepository.setPin(pin = pin)
    }

}