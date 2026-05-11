package com.littleapp.movies.screens.favorite

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.littleapp.movies.R
import com.littleapp.movies.Unit.DATA.IMAGE_MOVIE_BASIC
import com.littleapp.movies.Unit.DATA.MAIN
import com.littleapp.movies.databinding.ItemMovieBinding
import com.littleapp.movies.models.MovieItemModel

class FavoriteAdapter(private val context: Context) :
    RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    private var binding: ItemMovieBinding? = null
    var listMovies = emptyList<MovieItemModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemMovieBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding!!.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: MovieItemModel = listMovies[position]
        val title: String = model.title
        val date: String = model.release_date

        holder.tv_title.text = title
        holder.tv_date.text = date

        Glide.with(MAIN).load("$IMAGE_MOVIE_BASIC${listMovies[position].poster_path}")
            .placeholder(R.color.image_profile).into(holder.item_img)
    }

    override fun getItemCount(): Int {
        return listMovies.size
    }

    fun setList(list: List<MovieItemModel>) {
        listMovies = list
        notifyDataSetChanged()
    }

    override fun onViewAttachedToWindow(holder: ViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener {
            FavoriteFragment.clickMovie(listMovies[holder.adapterPosition])
        }
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        holder.itemView.setOnClickListener(null)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv_title: TextView
        var tv_date: TextView
        var item_img: ImageView
        //var item: LinearLayout

        init {
            tv_title = binding!!.tvTitle
            tv_date = binding!!.tvDate
            item_img = binding!!.itemImg
            //item = binding!!.item
        }
    }
}