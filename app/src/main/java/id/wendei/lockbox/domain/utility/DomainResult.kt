package id.wendei.lockbox.domain.utility

sealed class DomainResult<out T : Any> {

    data class Success<T : Any>(
        val data: T
    ) : DomainResult<T>()

    data class Error(
        val message: String
    ) : DomainResult<Nothing>()
}