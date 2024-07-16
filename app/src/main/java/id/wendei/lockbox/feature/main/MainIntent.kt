package id.wendei.lockbox.feature.main

sealed class MainIntent {
    data object LoadData: MainIntent()
}