package ru.cpt.fsp.ui.auth

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.cpt.fsp.domain.models.UserCredentials
import ru.cpt.fsp.domain.repositories.ApiRepository
import ru.cpt.fsp.ui.auth.analytics.AuthAnalyticsEvent
import ru.cpt.fsp.utils.base.BaseViewModel
import ru.cpt.fsp.utils.log.Logger
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    logger: Logger,
    private val apiRepository: ApiRepository,
) : BaseViewModel<AuthScreenState, AuthScreenEvent>(AuthScreenState(), logger) {

    fun onLoginChange(login: String) {
        updateState {
            it.copy(login = login)
        }
    }

    fun onPasswordChange(password: String) {
        updateState {
            it.copy(password = password)
        }
    }

    fun onAuth() = launchViewModelScope {
        updateState {
            it.copy(isLoading = true)
        }
        apiRepository.login(UserCredentials(currentState.login, currentState.password)).fold(
            onFailure = {
                logger.event(AuthAnalyticsEvent(false))
                sendEvent(AuthScreenEvent.ShowToast(it.message ?: "Something went wrong"))
            }, onSuccess = {
                logger.event(AuthAnalyticsEvent(true))
            }
        )
        updateState {
            AuthScreenState() //Сбрасываю на начальное состояние
        }
    }
}
