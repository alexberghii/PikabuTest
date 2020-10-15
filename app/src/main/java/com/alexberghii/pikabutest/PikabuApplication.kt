package com.alexberghii.pikabutest

import android.app.Application
import com.alexberghii.core.di.CoreComponent
import com.alexberghii.core.di.CoreComponentProvider
import com.alexberghii.core.di.DaggerCoreComponent
import com.alexberghii.pikabutest.di.DaggerAppComponent


class PikabuApplication : Application(), CoreComponentProvider {

    private lateinit var coreComponent: CoreComponent

    override fun provideCoreComponent() = coreComponent

    override fun onCreate() {
        super.onCreate()

        initCoreDi()
        initAppDi()
    }

    private fun initCoreDi() {
        coreComponent = DaggerCoreComponent.factory().create(this)
    }

    private fun initAppDi() {
        DaggerAppComponent.factory().create(coreComponent).inject(this)
    }
}