package ru.geekbrains.movie.repository

class RepositoryImpl: Repository {
    override fun getMovieFromServer(): Movie {
        TODO("Not yet implemented")
    }

    override fun getMovieFromLocal() = getMovies()
}