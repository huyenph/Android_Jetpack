package com.utildev.androidjetpack.common

import android.app.Application
import com.utildev.androidjetpack.di.networkModule
import com.utildev.androidjetpack.di.repositoryModule
import com.utildev.androidjetpack.di.sharedPrefModule
import org.koin.android.ext.android.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(
            this, listOf(
                networkModule,
                sharedPrefModule,
                repositoryModule
            )
        )
    }
}