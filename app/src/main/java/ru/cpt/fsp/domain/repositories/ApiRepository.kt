package ru.cpt.fsp.domain.repositories

import ru.cpt.fsp.domain.models.UserCredentials

interface ApiRepository {
    suspend fun login(userCredentials: UserCredentials): Result<Unit>
}