package id.wendei.lockbox.feature.main

import id.wendei.lockbox.domain.feature.password.model.Password

sealed class MainIntent {
    data object LoadData: MainIntent()
    data class NavigateToDetail(val password: Password): MainIntent()
    data object Copy: MainIntent()
}