package com.littleapp.movies.screens.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.littleapp.movies.R
import com.littleapp.movies.Unit.DATA
import com.littleapp.movies.Unit.DATA.MAIN
import com.littleapp.movies.databinding.FragmentMainMovieBinding
import com.littleapp.movies.models.MovieItemModel

class MainFragment : Fragment() {

    private var mBinding: FragmentMainMovieBinding? = null
    private val binding get() = mBinding!!
    lateinit var recyclerView: RecyclerView
    private val adapter by lazy { MainAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        mBinding = FragmentMainMovieBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
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
            MAIN.navController.navigate(R.id.action_mainFragment_to_favoriteFragment)
        }

        val viewModel = ViewModelProvider(this)[MainFragmentViewModel::class.java]
        viewModel.initDatabase()
        recyclerView = binding.rvMain
        recyclerView.adapter = adapter
        viewModel.getMoviesRetrofit()
        viewModel.myMovies.observe(viewLifecycleOwner) { list -> adapter.setList(list.body()!!.results) }
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }

    companion object {
        fun clickMovie(model: MovieItemModel) {
            val bundle = Bundle()
            bundle.putSerializable("movie", model)
            MAIN.navController.navigate(R.id.action_mainFragment_to_detailFragment, bundle)
        }
    }
}