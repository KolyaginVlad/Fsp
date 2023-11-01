package ru.cpt.fsp.data.api.interceptors

import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import ru.cpt.fsp.data.api.TokenHolder
import java.net.HttpURLConnection
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val tokenHolder: TokenHolder,
//    private val refreshApi: RefreshApi,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        var tryToRefreshToken = true
        while (true) {
            val token = tokenHolder.getAccessToken() ?: return createFailedResponseFromRequest(
                request,
                "Token null"
            )

            val response = chain.proceed(
                request
                    .newBuilder()
                    .addHeader(AUTHORIZATION_HEADER, BEARER_PREFIX + token)
                    .build()
            )

            if (tryToRefreshToken && response.code == HttpURLConnection.HTTP_UNAUTHORIZED) {
//                refreshApi.refresh(tokenHolder.getRefreshToken()).getOrNull()
//                    ?: return createFailedResponseFromRequest(
//                        request,
//                        "Refresh failed"
//                    )
                tryToRefreshToken = false
                response.close()
                continue
            }

            return response
        }
    }

    private fun createFailedResponseFromRequest(
        request: Request,
        message: String?,
    ): Response {
        val body = JsonObject(
            buildMap {
                put(ERROR_FIELD, JsonPrimitive(UNAUTHORIZED_ERROR_VALUE))
                if (message != null) {
                    put(MESSAGE_FIELD, JsonPrimitive(message))
                }
            }
        ).toString().toResponseBody("application/json".toMediaType())

        return Response.Builder()
            .code(HttpURLConnection.HTTP_UNAUTHORIZED)
            .body(body)
            .request(request)
            .protocol(Protocol.HTTP_1_1)
            .message("Auth error")
            .build()
    }

    private companion object {
        const val BEARER_PREFIX = "Bearer "
        const val AUTHORIZATION_HEADER = "Authorization"
        const val ERROR_FIELD = "error"
        const val MESSAGE_FIELD = "message"
        const val UNAUTHORIZED_ERROR_VALUE = "authentication_failed"
    }
}
