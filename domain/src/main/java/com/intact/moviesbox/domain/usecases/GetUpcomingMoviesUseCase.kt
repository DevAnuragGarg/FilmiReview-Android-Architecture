package com.intact.moviesbox.domain.usecases

import com.intact.moviesbox.domain.entities.UpcomingMoviesDomainDTO
import com.intact.moviesbox.domain.repositories.MovieRepository
import com.intact.moviesbox.domain.schedulers.BaseSchedulerProvider
import com.intact.moviesbox.domain.usecases.base.ObservableUseCase
import io.reactivex.Observable
import javax.inject.Inject

class GetUpcomingMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    schedulerProvider: BaseSchedulerProvider
) : ObservableUseCase<UpcomingMoviesDomainDTO, GetUpcomingMoviesUseCase.Param>(schedulerProvider) {

    // this data class will hold the data which is required to
    data class Param(val pageNumber: String)

    override fun generateObservable(params: Param?): Observable<UpcomingMoviesDomainDTO> {
        if (params == null) {
            throw IllegalArgumentException("PopularMoviesUseCase parameter can't be null")
        }
        return movieRepository.getUpcomingRatedMovies(pageNumber = params.pageNumber)
    }
}