package com.fitpeo.sample

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.fitpeo.sample.databinding.AdapterMovieBinding

class MainAdapter : RecyclerView.Adapter<MainViewHolder>() {

    var movies = mutableListOf<Movie>()
    lateinit var mActivity: Activity

    fun setMovieList(movies: List<Movie>, activity: Activity) {
        this.movies = movies.toMutableList()
        this.mActivity = activity
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = AdapterMovieBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val movie = movies[position]
        holder.binding.name.text = movie.title
        Glide.with(holder.itemView.context).load(movie.thumbnailUrl).apply(
            RequestOptions()
                .placeholder(R.drawable.ic_launcher_foreground)
        ).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.binding.imageview)
        holder.itemView.setOnClickListener {
            val intent = Intent(mActivity, DetailsActivity::class.java)
            intent.putExtra("albumId", movie.albumId)
            intent.putExtra("id", movie.id)
            intent.putExtra("title", movie.title)
            intent.putExtra("url", movie.url)
            intent.putExtra("thumbnailUrl", movie.thumbnailUrl)
            mActivity.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}

class MainViewHolder(val binding: AdapterMovieBinding) : RecyclerView.ViewHolder(binding.root) {

}
