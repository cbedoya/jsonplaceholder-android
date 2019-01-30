package com.cbedoya.jsonplaceholder.di

import com.cbedoya.jsonplaceholder.app.App
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class, BuildersModule::class, AppModule::class, NetworkModule::class]
)
interface AppComponent {
    fun inject(app: App)
}