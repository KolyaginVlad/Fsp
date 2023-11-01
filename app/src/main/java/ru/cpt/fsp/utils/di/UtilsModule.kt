package ru.cpt.fsp.utils.di

import android.content.Context
import ru.cpt.fsp.utils.log.Logger
import ru.cpt.fsp.utils.log.LoggerImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface UtilsModule {

    @Binds
    fun bindLogger(loggerImpl: LoggerImpl): Logger

    companion object {
//        @Provides
//        fun provideFirebaseAnalytics(@ApplicationContext context: Context): FirebaseAnalytics {
//            return FirebaseAnalytics.getInstance(context)
//        }
    }
}