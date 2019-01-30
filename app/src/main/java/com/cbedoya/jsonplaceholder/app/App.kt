package com.cbedoya.jsonplaceholder.app

import android.app.Activity
import android.app.Application
import com.cbedoya.jsonplaceholder.BuildConfig
import com.cbedoya.jsonplaceholder.di.AppModule
import com.cbedoya.jsonplaceholder.di.DaggerAppComponent
import com.cbedoya.jsonplaceholder.di.NetworkModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class App: Application(), HasActivityInjector {
    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .networkModule(NetworkModule(BuildConfig.BASE_URL))
            .build().inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector
}