package ru.cpt.fsp.ui.auth

import ru.cpt.fsp.utils.base.State

data class AuthScreenState(
    val login: String = "",
    val password: String = "",
    val isLoading: Boolean = false
) : State()