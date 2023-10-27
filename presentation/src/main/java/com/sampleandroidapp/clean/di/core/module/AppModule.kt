package com.sampleandroidapp.clean.di.core.module

import android.content.Context
import android.content.SharedPreferences
import androidx.work.WorkManager
import com.sampleandroidapp.clean.di.core.AppSettingsSharedPreference
import com.sampleandroidapp.clean.util.NetworkMonitor
import com.sampleandroidapp.clean.util.ResourceProvider
import com.sampleandroidapp.data.util.DiskExecutor
import com.sampleandroidapp.data.util.DispatchersProvider
import com.sampleandroidapp.data.util.DispatchersProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Ali Asadi on 15/05/2020
 **/

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideDiskExecutor(): DiskExecutor {
        return DiskExecutor()
    }

    @Provides
    fun provideDispatchersProvider(): DispatchersProvider {
        return DispatchersProviderImpl
    }

    @Provides
    fun provideResourceProvider(@ApplicationContext context: Context): ResourceProvider {
        return ResourceProvider(context)
    }

    @Provides
    @AppSettingsSharedPreference
    fun provideAppSettingsSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("AppSettings", Context.MODE_PRIVATE)
    }

    @Provides
    fun provideWorkManager(
        @ApplicationContext context: Context
    ): WorkManager = WorkManager.getInstance(context)

    @Provides
    @Singleton
    fun provideNetworkMonitor(
        @ApplicationContext context: Context
    ): NetworkMonitor = NetworkMonitor(context)
}