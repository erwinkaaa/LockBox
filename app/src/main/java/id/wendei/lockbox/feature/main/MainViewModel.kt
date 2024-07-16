package id.wendei.lockbox.feature.main

import id.wendei.lockbox.core.util.BaseViewModel

class MainViewModel: BaseViewModel<MainState, MainEvent, MainIntent>(MainState()) {

    override fun onIntent(intent: MainIntent) {
        when (intent) {
            else -> {}
        }
    }

}