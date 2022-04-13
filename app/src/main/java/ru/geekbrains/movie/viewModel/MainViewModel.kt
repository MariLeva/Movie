package ru.geekbrains.movie.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.geekbrains.movie.repository.AppState
import ru.geekbrains.movie.repository.RepositoryImpl
import java.lang.Thread.sleep

class MainViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData(),
    private val repositoryImpl: RepositoryImpl = RepositoryImpl()
) : ViewModel() {

    fun getData(): LiveData<AppState> = liveData

    fun getMovieFromServer() = getMovieFromRemote()

    fun getMovieFromLocal() = getMovieLocal()

    private fun getMovieFromRemote(){
        liveData.value = AppState.Loading
        liveData.postValue(AppState.Error(Throwable()))
    }

    private fun getMovieLocal(){
        liveData.value = AppState.Loading
        Thread{
            sleep(1000)
            liveData.postValue(AppState.Success(repositoryImpl.getMovieFromLocal()))}.start()
    }
}