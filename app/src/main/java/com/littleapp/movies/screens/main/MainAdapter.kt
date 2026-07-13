package com.littleapp.movies.screens.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.littleapp.movies.R
import com.littleapp.movies.utils.DATA.IMAGE_MOVIE_BASIC
import com.littleapp.movies.databinding.ItemMovieBinding
import com.littleapp.movies.models.MovieItemModel

class MainAdapter(private val onMovieClick: (MovieItemModel) -> Unit) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<MovieItemModel>() {
        override fun areItemsTheSame(oldItem: MovieItemModel, newItem: MovieItemModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieItemModel, newItem: MovieItemModel): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var listMovies: List<MovieItemModel>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listMovies[position])
    }

    override fun getItemCount(): Int = listMovies.size

    inner class ViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: MovieItemModel) {
            binding.tvTitle.text = model.title
            binding.tvDate.text = model.release_date

            Glide.with(itemView.context)
                .load("$IMAGE_MOVIE_BASIC${model.poster_path}")
                .placeholder(R.color.image_profile)
                .into(binding.itemImg)

            itemView.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onMovieClick(listMovies[position])
                }
            }
        }

        fun unbind() {
            itemView.setOnClickListener(null)
        }
    }

    override fun onViewRecycled(holder: ViewHolder) {
        super.onViewRecycled(holder)
        holder.unbind()
    }
}