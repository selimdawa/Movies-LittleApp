package com.littleapp.movies.screens.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.littleapp.movies.R
import com.littleapp.movies.databinding.FragmentDetailMovieBinding
import com.littleapp.movies.models.MovieItemModel
import com.littleapp.movies.utils.DATA.IMAGE_MOVIE
import com.littleapp.movies.utils.loadImage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailMovieBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailViewModel by hiltNavGraphViewModels(R.id.nav_graph)
    private val args: DetailFragmentArgs by navArgs()

    private lateinit var currentMovie: MovieItemModel
    private var isFavorite = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDetailMovieBinding.inflate(inflater, container, false)
        currentMovie = args.movie
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        binding.toolbar.nameSpace.text = getString(R.string.details_movie)

        viewLifecycleOwner.lifecycleScope.launch {
            isFavorite = viewModel.isFavorite(currentMovie.id)
            updateFavoriteIcon()
        }

        binding.imgDetail.loadImage("$IMAGE_MOVIE${currentMovie.poster_path}")

        binding.tvTitleDetail.text = currentMovie.title
        binding.tvDateDetail.text = currentMovie.release_date
        binding.tvDescription.text = currentMovie.overview

        binding.imgDetailFavorite.setOnClickListener {
            viewModel.toggleFavorite(currentMovie, isFavorite)
            isFavorite = !isFavorite
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