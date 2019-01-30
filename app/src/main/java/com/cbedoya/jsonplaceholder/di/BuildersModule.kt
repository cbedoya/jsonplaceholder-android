package com.cbedoya.jsonplaceholder.di

import com.cbedoya.jsonplaceholder.view.PostListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): PostListActivity
}