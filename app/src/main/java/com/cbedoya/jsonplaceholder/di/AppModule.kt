package com.cbedoya.jsonplaceholder.di

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.cbedoya.jsonplaceholder.viewmodel.PostsViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: Application) {
    @Provides
    @Singleton
    fun provideApplication(): Application = app

    @Provides
    fun providePostsViewModelFactory(
        factory: PostsViewModelFactory
    ): ViewModelProvider.Factory = factory
}
