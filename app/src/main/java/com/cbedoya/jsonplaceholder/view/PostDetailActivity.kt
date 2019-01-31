package com.cbedoya.jsonplaceholder.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.cbedoya.jsonplaceholder.R
import com.cbedoya.jsonplaceholder.databinding.ActivityPostDetailBinding
import com.cbedoya.jsonplaceholder.viewmodel.PostViewModel

class PostDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding:ActivityPostDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_post_detail)
        val post: PostViewModel = intent.getParcelableExtra("post")
        binding.post = post
    }
}
