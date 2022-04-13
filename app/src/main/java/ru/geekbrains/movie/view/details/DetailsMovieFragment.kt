package ru.geekbrains.movie.view.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.geekbrains.movie.R
import ru.geekbrains.movie.databinding.FragmentDetailsMovieBinding
import ru.geekbrains.movie.repository.Movie
import ru.geekbrains.movie.utlis.KEY_BUNDLE_MOVIE

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailsMovieFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailsMovieFragment : Fragment() {
    private var _binding : FragmentDetailsMovieBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(false)
        arguments?.getParcelable<Movie>(KEY_BUNDLE_MOVIE)?.let {
            with(binding){
                tvNameMovie.text = it.name
                tvCountry.text = it.country
                tvGenre.text = it.genre
                year.text = it.year.toString()
                imageMovie.setImageResource(it.poster)
            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(bundle: Bundle) =
            DetailsMovieFragment().apply {
                arguments = bundle
            }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}