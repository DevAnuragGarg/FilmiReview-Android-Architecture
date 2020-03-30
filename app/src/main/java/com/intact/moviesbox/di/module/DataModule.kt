package com.intact.moviesbox.di.module

import com.intact.moviesbox.data.mapper.*
import com.intact.moviesbox.data.model.*
import com.intact.moviesbox.data.repository.MovieRepositoryImpl
import com.intact.moviesbox.domain.entities.*
import com.intact.moviesbox.domain.repositories.MovieRepository
import dagger.Binds
import dagger.Module

/**
 * dagger module having the dependencies listed here
 */
@Module
abstract class DataModule {

    @Binds
    abstract fun bindsRepository(repositoryImpl: MovieRepositoryImpl): MovieRepository

    @Binds
    abstract fun bindsMovieDomainDataMapper(movieDomainDataMapper: MovieDomainDataMapper): Mapper<MovieEntity, MovieData>

    @Binds
    abstract fun bindsPopularDomainDataMapper(popularDomainDataMapper: PopularDomainDataMapper): Mapper<PopularMoviesEntity, PopularMoviesData>

    @Binds
    abstract fun bindsTopRatedDomainDataMapper(topRatedDomainDataMapper: TopRatedDomainDataMapper): Mapper<TopRatedMoviesEntity, TopRatedMoviesData>

    @Binds
    abstract fun bindsTrendingDomainDataMapper(trendingDomainDataMapper: TrendingDomainDataMapper): Mapper<TrendingMoviesEntity, TrendingMoviesData>

    @Binds
    abstract fun bindsUpcomingDomainDataMapper(upcomingDomainDataMapper: UpcomingDomainDataMapper): Mapper<UpcomingMoviesEntity, UpcomingMoviesData>

    @Binds
    abstract fun bindsNowPlayingDomainDataMapper(nowPlayingDomainDataMapper: NowPlayingDomainDataMapper): Mapper<NowPlayingMoviesEntity, NowPlayingMoviesData>
}