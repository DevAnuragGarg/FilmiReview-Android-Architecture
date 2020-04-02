package com.intact.moviesbox.domain.usecases

import com.intact.moviesbox.domain.entities.PopularMoviesDomainDTO
import com.intact.moviesbox.domain.repositories.MovieRepository
import com.intact.moviesbox.domain.schedulers.BaseSchedulerProvider
import com.intact.moviesbox.domain.usecases.base.ObservableUseCase
import io.reactivex.Observable
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository, schedulerProvider: BaseSchedulerProvider
) : ObservableUseCase<PopularMoviesDomainDTO, GetPopularMoviesUseCase.Param>(schedulerProvider) {

    /**
     * implementing the base function to generate observable
     * an overriding function does not allow to provide default value to parameters
     */
    override fun generateObservable(params: Param?): Observable<PopularMoviesDomainDTO> {
        if (params == null) {
            throw IllegalArgumentException("PopularMoviesUseCase parameter can't be null")
        }
        return movieRepository.getPopularMovies(pageNumber = params.pageNumber)
    }

    /**
     * data class used as a input to fetch the list of popular movies
     */
    data class Param(
        val pageNumber: String
    )
}