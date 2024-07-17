package id.wendei.lockbox.feature.form

sealed class FormEvent {
    data object Back: FormEvent()
}