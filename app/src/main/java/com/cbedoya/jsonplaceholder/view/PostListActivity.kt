package com.cbedoya.jsonplaceholder.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cbedoya.jsonplaceholder.R
import com.cbedoya.jsonplaceholder.databinding.ActivityPostListBinding
import com.cbedoya.jsonplaceholder.model.PostModel
import com.cbedoya.jsonplaceholder.viewmodel.PostListViewModel
import com.cbedoya.jsonplaceholder.viewmodel.PostViewModel
import com.cbedoya.jsonplaceholder.viewmodel.PostsViewModelFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_post_list.*
import javax.inject.Inject

class PostListActivity : AppCompatActivity() {

    @Inject
    lateinit var postsViewModelFactory: PostsViewModelFactory
    private lateinit var viewModel: PostListViewModel

    private val loadingObserver = Observer<Boolean> {
        progressBar.visibility = View.GONE
    }

    private val dataObserver = Observer<List<PostModel>> { posts ->
        postsRecyclerView.adapter = PostAdapter(posts) { postItemClicked(it) }
    }

    private val errorObserver = Observer<String> { error ->

    }

    private fun postItemClicked(post: PostViewModel) {
        val intent = Intent(this, PostDetailActivity::class.java)
        intent.putExtra("post", post)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityPostListBinding? = DataBindingUtil.setContentView(this, R.layout.activity_post_list)
        AndroidInjection.inject(this)

        postsRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        viewModel = ViewModelProviders.of(this, postsViewModelFactory).get(PostListViewModel::class.java)
        viewModel.loader.observe(this, loadingObserver)
        viewModel.data.observe(this, dataObserver)

        binding?.let {
            it.viewModel = viewModel
        }
    }
}
