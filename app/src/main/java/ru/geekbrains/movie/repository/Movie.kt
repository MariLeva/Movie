package ru.geekbrains.movie.repository

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import ru.geekbrains.movie.R

@Parcelize
data class Movie(
    val name: String, val year: Int,
    val country: String, val genre: String,
    val poster: Int
) : Parcelable

fun getMovies() = listOf(
    Movie(
        "Темный рыцарь", 2008, "США, Великобритания",
        "фантастика, боевик, триллер, криминал, драма", R.drawable.dark_knight
    ),
    Movie(
        "Темный рыцарь. Возрождение легенды", 2012, "США, Великобритания",
        "фантастика, боевик, триллер, криминал, драма", R.drawable.dark_knight_rises_poster
    ),
    Movie(
        "Бэтмен: Начало", 2005, "США, Великобритания",
        "фантастика, боевик, триллер, криминал, драма", R.drawable.batman_begin
    ),
    Movie(
        "Лего Фильм: Бэтмен", 2017, "США, Дания, Австралия",
        "мультфильм, фантастика, фэнтези, боевик, комедия, приключения, семейный", R.drawable.lego_batman
    ),
    Movie(
        "Бэтмен", 2022, "США",
        "боевик, драма, криминал, детектив", R.drawable.batman_2022
    )
)