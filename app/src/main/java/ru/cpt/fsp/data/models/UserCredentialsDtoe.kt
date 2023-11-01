package ru.cpt.fsp.data.models

import androidx.annotation.Keep
import kotlinx.serialization.Serializable
import ru.cpt.fsp.domain.models.UserCredentials

@Serializable
@Keep
data class UserCredentialsDtoe(
    val login: String,
    val password: String
)

fun UserCredentials.mapToDtoe() =
    UserCredentialsDtoe(login, password)