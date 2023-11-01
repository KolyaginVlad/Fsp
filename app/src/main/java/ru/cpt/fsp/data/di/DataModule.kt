package ru.cpt.fsp.data.di

import android.content.Context
import android.content.SharedPreferences
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import ru.cpt.fsp.data.api.call.ResultCallAdapterFactory
import ru.cpt.fsp.data.repositories.ApiRepositoryImpl
import ru.cpt.fsp.domain.repositories.ApiRepository
import ru.cpt.fsp.utils.Constants
import java.time.Duration
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface DataModule {
    @Binds
    fun bindApiRepository(
        apiRepositoryImpl: ApiRepositoryImpl,
    ): ApiRepository

    companion object {
        private const val TIMEOUT_VALUE = 20L

        @Singleton
        @Provides
        fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
            return context.getSharedPreferences("Fsp", Context.MODE_PRIVATE)
        }

        @Singleton
        @Provides
        fun provideRetrofit(client: OkHttpClient, converterFactory: Converter.Factory): Retrofit {
            return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(ResultCallAdapterFactory())
                .client(client)
                .build()
        }

        @Provides
        fun provideConverterFactory(): Converter.Factory {
            val contentType = "application/json".toMediaType()
            val json = Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
            }
            return json.asConverterFactory(contentType)
        }

        @Provides
        @Singleton
        fun provideOkHttpClient(): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(
                    HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
                )
                .callTimeout(Duration.ofSeconds(TIMEOUT_VALUE))
                .connectTimeout(Duration.ofSeconds(TIMEOUT_VALUE))
                .build()
        }
    }
}