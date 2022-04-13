package ru.geekbrains.movie.repository

sealed class AppState{
    data class Success(val movieData: List<Movie>) : AppState()
    data class Error(val error: Throwable): AppState()
    object Loading: AppState()
}
