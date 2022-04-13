package ru.geekbrains.movie.view.movieList

import ru.geekbrains.movie.repository.Movie

interface OnItemClickListener {
    fun onItemClick(movie: Movie)
}