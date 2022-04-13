package ru.geekbrains.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.geekbrains.movie.view.movieList.MovieFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savedInstanceState.let {
            supportFragmentManager.beginTransaction().replace(R.id.container, MovieFragment.newInstance()).commit()
        }
    }
}