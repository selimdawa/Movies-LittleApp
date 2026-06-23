package com.littleapp.movies.screens.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.littleapp.movies.R
import com.littleapp.movies.Unit.DATA
import com.littleapp.movies.Unit.DATA.IMAGE_MOVIE
import com.littleapp.movies.Unit.DATA.MAIN
import com.littleapp.movies.databinding.FragmentDetailMovieBinding
import com.littleapp.movies.SaveShared
import com.littleapp.movies.models.MovieItemModel

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailMovieBinding? = null
    private val binding get() = _binding!!

    private lateinit var currentMovie: MovieItemModel
    private var isFavorite = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDetailMovieBinding.inflate(inflater, container, false)
        currentMovie = arguments?.getSerializable("movie") as MovieItemModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        binding.toolbar.nameSpace.text = DATA.Details_Movie
        val valueBool = SaveShared.getFavorite(MAIN, currentMovie.id.toString())
        val viewModel = ViewModelProvider(this)[DetailViewModel::class.java]

        isFavorite = valueBool
        updateFavoriteIcon()

        Glide.with(this)
            .load("$IMAGE_MOVIE${currentMovie.poster_path}")
            .placeholder(R.color.image_profile)
            .into(binding.imgDetail)

        binding.tvTitleDetail.text = currentMovie.title
        binding.tvDateDetail.text = currentMovie.release_date
        binding.tvDescription.text = currentMovie.overview

        binding.imgDetailFavorite.setOnClickListener {
            if (!isFavorite) {
                SaveShared.setFavorite(MAIN, currentMovie.id.toString(), true)
                viewModel.insert(currentMovie) {}
                isFavorite = true
            } else {
                viewModel.delete(currentMovie) {}
                SaveShared.setFavorite(MAIN, currentMovie.id.toString(), false)
                isFavorite = false
            }
            updateFavoriteIcon()
        }
    }

    private fun updateFavoriteIcon() {
        val iconRes = if (isFavorite) {
            R.drawable.ic_baseline_favorite_24
        } else {
            R.drawable.ic_baseline_favorite_border_24
        }
        binding.imgDetailFavorite.setImageResource(iconRes)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}