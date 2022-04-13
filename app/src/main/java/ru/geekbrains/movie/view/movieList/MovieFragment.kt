package ru.geekbrains.movie.view.movieList

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_movie.*
import ru.geekbrains.movie.R
import ru.geekbrains.movie.databinding.FragmentMovieBinding
import ru.geekbrains.movie.repository.AppState
import ru.geekbrains.movie.repository.Movie
import ru.geekbrains.movie.utlis.KEY_BUNDLE_MOVIE
import ru.geekbrains.movie.view.details.DetailsMovieFragment
import ru.geekbrains.movie.viewModel.MainViewModel


class MovieFragment : Fragment(), OnItemClickListener {

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!
    private val adapter = MovieListAdapter(this)

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        binding.recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val observer = Observer<AppState> { renderData (it)}
        viewModel.getData().observe(viewLifecycleOwner, observer)
        viewModel.getMovieFromLocal()
    }

    private fun renderData(data: AppState){
        when (data){
            is AppState.Success -> {
                binding.loading.visibility = View.GONE
                adapter.setData(data.movieData)
            }
            is AppState.Loading -> {
                binding.loading.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.loading.visibility = View.GONE
                Snackbar.make(binding.root, "Север не доступен ${data.error}", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MovieFragment()
    }

    override fun onItemClick(movie: Movie) {
        requireActivity().supportFragmentManager.beginTransaction().add(
            R.id.container, DetailsMovieFragment.newInstance(Bundle().apply {
                putParcelable(KEY_BUNDLE_MOVIE, movie)
            })
        ).addToBackStack("").commit()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.local_server ->
                binding.movieFragment.showSnackBarAction(getString(R.string.local_server), 0, getString(R.string.open),
                    {viewModel.getMovieFromLocal()})
            R.id.server ->
                binding.movieFragment.showSnackBarAction(getString(R.string.remote_server),0, getString(R.string.open),
                    { viewModel.getMovieFromServer()})
        }
        return super.onOptionsItemSelected(item)
    }

    private fun View.showSnackBarAction(
        text: String,
        length: Int = Snackbar.LENGTH_INDEFINITE,
        actionText: String,
        action: (View) -> Unit) {
        Snackbar.make(this, text, length).setAction(actionText, action).show()
    }
}