package com.fitpeo.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.fitpeo.sample.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var binding: ActivityDetailsBinding
    var albumId: Int = 0
    var id: Int = 0
    var title: String = ""
    var url: String = ""
    var thumbnailUrl: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            albumId = bundle.getInt("albumId", 0)
            id = bundle.getInt("id", 0)
            title = bundle.getString("title", "")
            url = bundle.getString("url", "")
            thumbnailUrl = bundle.getString("thumbnailUrl", "")

            binding.name.text = title
            Glide.with(this).load(url).apply(
                RequestOptions()
                    .placeholder(R.drawable.ic_launcher_foreground)
            ).diskCacheStrategy(DiskCacheStrategy.ALL).into(binding.imageview)
        }
    }
}