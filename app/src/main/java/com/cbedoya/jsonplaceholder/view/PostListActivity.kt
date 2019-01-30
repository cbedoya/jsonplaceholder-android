package com.cbedoya.jsonplaceholder.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import com.cbedoya.jsonplaceholder.R
import com.cbedoya.jsonplaceholder.viewmodel.PostListViewModel
import com.cbedoya.jsonplaceholder.viewmodel.PostsViewModelFactory
import dagger.android.AndroidInjection
import javax.inject.Inject

class PostListActivity : AppCompatActivity() {

    @Inject
    lateinit var postsViewModelFactory: PostsViewModelFactory
    lateinit var viewModel: PostListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)

        viewModel = ViewModelProviders.of(this, postsViewModelFactory).get(PostListViewModel::class.java)
    }
}
