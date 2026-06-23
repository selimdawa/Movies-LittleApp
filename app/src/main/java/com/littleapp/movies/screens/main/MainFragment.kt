package com.littleapp.movies.screens.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.littleapp.movies.R
import com.littleapp.movies.Unit.DATA
import com.littleapp.movies.databinding.FragmentMainMovieBinding

class MainFragment : Fragment() {

    private var _binding: FragmentMainMovieBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy {
        MainAdapter { movie ->
            val bundle = Bundle().apply {
                putSerializable("movie", movie)
            }
            findNavController().navigate(R.id.action_mainFragment_to_detailFragment, bundle)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMainMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        binding.toolbar.nameSpace.text = DATA.MOVIE
        binding.toolbar.imageLeft.visibility = View.VISIBLE
        binding.toolbar.imageLeft.setImageResource(R.drawable.ic_baseline_favorite_24)

        binding.toolbar.imageLeft.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_favoriteFragment)
        }

        binding.rvMain.adapter = adapter

        val viewModel = ViewModelProvider(this)[MainFragmentViewModel::class.java]
        viewModel.initDatabase()
        viewModel.getMoviesRetrofit()

        viewModel.myMovies.observe(viewLifecycleOwner) { response ->
            val moviesBody = response?.body()
            if (moviesBody != null) {
                adapter.listMovies = moviesBody.results
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}