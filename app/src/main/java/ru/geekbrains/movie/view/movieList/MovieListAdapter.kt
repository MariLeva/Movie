package ru.geekbrains.movie.view.movieList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.movie.R
import ru.geekbrains.movie.databinding.FragmentMovieRecyclerItemBinding
import ru.geekbrains.movie.repository.Movie

class MovieListAdapter(private val onItemClickListener: OnItemClickListener)
    : RecyclerView.Adapter<MovieListAdapter.MovieListAdapter>() {

    private var data: List<Movie> = listOf()

    fun setData(movie: List<Movie>){
        this.data = movie
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListAdapter {
        val builder = FragmentMovieRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MovieListAdapter(builder.root)
    }

    override fun onBindViewHolder(holder: MovieListAdapter, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size


    inner class MovieListAdapter(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(movie: Movie){
            FragmentMovieRecyclerItemBinding.bind(itemView).apply {
                tvNameMovie.text = movie.name
                imageMovie.setImageResource(movie.poster)
                root.setOnClickListener{
                    onItemClickListener.onItemClick(movie)
                }
            }
        }
    }
}