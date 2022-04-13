package ru.geekbrains.movie.repository

interface Repository {
    fun getMovieFromServer() : Movie
    fun getMovieFromLocal() : List<Movie>
}