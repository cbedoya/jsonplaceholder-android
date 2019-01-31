package com.cbedoya.jsonplaceholder.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cbedoya.jsonplaceholder.R
import com.cbedoya.jsonplaceholder.databinding.ActivityPostDetailBinding
import com.cbedoya.jsonplaceholder.model.CommentModel
import com.cbedoya.jsonplaceholder.viewmodel.*
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_post_detail.*
import javax.inject.Inject

class PostDetailActivity : AppCompatActivity() {

    @Inject
    lateinit var commentsViewModelFactory: CommentsViewModelFactory
    private lateinit var viewModel: CommentListViewModel

    private val loadingObserver = Observer<Boolean> {

    }

    private val dataObserver = Observer<List<CommentModel>> { comments ->
        commentsRecyclerView.adapter = CommentAdapter(comments)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding:ActivityPostDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_post_detail)
        AndroidInjection.inject(this)

        commentsRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        val postId: Int = intent.getIntExtra("post_id", 1)
        viewModel = ViewModelProviders.of(this, commentsViewModelFactory).get(CommentListViewModel::class.java)
        viewModel.loader.observe(this, loadingObserver)
        viewModel.data.observe(this, dataObserver)
        viewModel.fetch(postId)

        binding?.let {
            it.viewModel = viewModel
        }
    }
}
