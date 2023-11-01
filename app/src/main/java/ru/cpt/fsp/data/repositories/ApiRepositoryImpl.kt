package ru.cpt.fsp.data.repositories

import kotlinx.coroutines.delay
import ru.cpt.fsp.data.api.TokenHolder
import ru.cpt.fsp.data.models.mapToDtoe
import ru.cpt.fsp.domain.models.UserCredentials
import ru.cpt.fsp.domain.repositories.ApiRepository
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val tokenHolder: TokenHolder,
) : ApiRepository {
    override suspend fun login(userCredentials: UserCredentials): Result<Unit> {
        val user = userCredentials.mapToDtoe()
        delay(1000)
        return if (Math.random() > 0.5) {
            tokenHolder.saveAccessToken("TODO")
            tokenHolder.saveRefreshToken("TODO")
            Result.success(Unit)
        } else {
            Result.failure(IllegalAccessException())
        }
    }
}
