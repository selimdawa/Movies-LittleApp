package com.littleapp.movies.screens.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.littleapp.movies.R
import com.littleapp.movies.Unit.DATA
import com.littleapp.movies.databinding.FragmentFavoriteMovieBinding

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteMovieBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy {
        FavoriteAdapter { movie ->
            val bundle = Bundle().apply {
                putSerializable("movie", movie)
            }
            findNavController().navigate(R.id.action_favoriteFragment_to_detailFragment, bundle)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFavoriteMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        binding.toolbar.nameSpace.text = DATA.Favorite_movies
        binding.rvFavorite.adapter = adapter

        val viewModel = ViewModelProvider(this)[FavoriteFragmentViewModel::class.java]
        viewModel.getAllMovies().observe(viewLifecycleOwner) { list ->
            adapter.listMovies = list.asReversed()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}