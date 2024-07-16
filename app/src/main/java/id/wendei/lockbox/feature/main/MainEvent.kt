package id.wendei.lockbox.feature.main

sealed class MainEvent {
    data class GoToDetail(val detail: String): MainEvent()
}