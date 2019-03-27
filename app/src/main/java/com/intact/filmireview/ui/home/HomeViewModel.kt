package com.intact.filmireview.ui.home

import androidx.lifecycle.MutableLiveData
import com.intact.filmireview.data.BaseDataManager
import com.intact.filmireview.ui.BaseViewModel
import com.intact.filmireview.ui.model.ErrorDTO
import com.intact.filmireview.ui.model.PopularMovieDTO
import com.intact.filmireview.util.scheduler.BaseSchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject


/**
 *
 * view model defined for the home screen
 *
 * Created by Anurag Garg on 25/03/19.
 */

class HomeViewModel @Inject constructor(
    dataManager: BaseDataManager,
    compositeDisposable: CompositeDisposable,
    schedulerProvider: BaseSchedulerProvider
) : BaseViewModel(dataManager, compositeDisposable, schedulerProvider) {

    private val errorLiveData = MutableLiveData<ErrorDTO>()
    private val popularMoviesLiveData = MutableLiveData<ArrayList<PopularMovieDTO>>()

    // get the popular movies
    fun getPopularMovies() {
        getLoadingLiveData().value = true
        getCompositeDisposable().add(
            getBaseDataManager().getPopularMovies("1")
                .subscribeOn(getBaseSchedulerProvider().io())
                .observeOn(getBaseSchedulerProvider().ui())
                .subscribe({ it ->
                    Timber.d("Success: Popular movies response received: ${it.popularMovies}")
                    popularMoviesLiveData.value = it.popularMovies
                }, {
                    val error = it as HttpException
                    Timber.d("ErrorCode: ${error.code()} & Failure: ${error.localizedMessage}")
                    errorLiveData.value = ErrorDTO(code = error.code(), message = error.localizedMessage)
                })
        )
    }

    fun getErrorLiveData() = errorLiveData
    fun getPopularMoviesLiveData() = popularMoviesLiveData

    override fun onCleared() {
        super.onCleared()
        getCompositeDisposable().clear()
    }
}