package com.littleapp.movies.screens.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.findNavController
import com.littleapp.movies.R
import com.littleapp.movies.databinding.FragmentFavoriteMovieBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteMovieBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FavoriteFragmentViewModel by hiltNavGraphViewModels(R.id.nav_graph)

    private val adapter by lazy {
        FavoriteAdapter { movie ->
            val action = FavoriteFragmentDirections.actionFavoriteFragmentToDetailFragment(movie)
            findNavController().navigate(action)
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
        binding.toolbar.nameSpace.text = getString(R.string.favorite_movies)
        binding.rvFavorite.adapter = adapter

        viewModel.getAllMovies().observe(viewLifecycleOwner) { list ->
            adapter.listMovies = list.asReversed()
            binding.tvEmpty.visibility = if (list.isEmpty()) View.VISIBLE else View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}