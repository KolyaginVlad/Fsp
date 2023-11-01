package ru.cpt.fsp.ui.auth

import ru.cpt.fsp.utils.base.Event

sealed class AuthScreenEvent : Event() {
    class ShowToast(val text: String) : AuthScreenEvent()
}