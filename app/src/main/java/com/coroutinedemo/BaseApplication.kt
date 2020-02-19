package com.coroutinedemo

import android.app.Application
import timber.log.Timber

/**
 * Created by shivanggoel on 19,February,2020
 */
class BaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        // initialize logger
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}