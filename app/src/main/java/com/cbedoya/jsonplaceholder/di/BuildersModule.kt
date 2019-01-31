package com.cbedoya.jsonplaceholder.di

import com.cbedoya.jsonplaceholder.view.PostDetailActivity
import com.cbedoya.jsonplaceholder.view.PostListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {

    @ContributesAndroidInjector
    abstract fun contributePostListActivity(): PostListActivity

    @ContributesAndroidInjector
    abstract fun contributePostDetailActivity(): PostDetailActivity
}