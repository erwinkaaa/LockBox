package id.wendei.lockbox.feature.main

import id.wendei.lockbox.domain.feature.password.model.Password

sealed class MainEvent {
    data class GoToDetail(val password: Password): MainEvent()
}