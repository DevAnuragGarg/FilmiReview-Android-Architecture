package com.intact.moviesbox.data.mapper

import com.intact.moviesbox.data.model.MovieDataDTO
import com.intact.moviesbox.domain.entities.MovieDomainDTO
import javax.inject.Inject

/**
 * mapper file to map the values from or to MovieEntity and MovieData
 */

class MovieDomainDataMapper @Inject constructor() : Mapper<MovieDomainDTO, MovieDataDTO> {

    override fun from(e: MovieDataDTO): MovieDomainDTO {
        return MovieDomainDTO(
            id = e.id,
            title = e.title,
            adult = e.adult,
            budget = e.budget,
            status = e.status,
            imdbId = e.imdbId,
            runtime = e.runtime,
            revenue = e.revenue,
            tagLine = e.tagLine,
            overview = e.overview,
            voteCount = e.voteCount,
            popularity = e.popularity,
            posterPath = e.posterPath,
            voteAverage = e.voteAverage,
            releaseDate = e.releaseDate,
            backdropPath = e.backdropPath,
            originalTitle = e.originalTitle,
            originalLanguage = e.originalLanguage
        )
    }

    override fun to(t: MovieDomainDTO): MovieDataDTO {
        return MovieDataDTO(
            id = t.id,
            title = t.title,
            adult = t.adult,
            budget = t.budget,
            status = t.status,
            imdbId = t.imdbId,
            runtime = t.runtime,
            revenue = t.revenue,
            tagLine = t.tagLine,
            overview = t.overview,
            voteCount = t.voteCount,
            popularity = t.popularity,
            posterPath = t.posterPath,
            voteAverage = t.voteAverage,
            releaseDate = t.releaseDate,
            backdropPath = t.backdropPath,
            originalTitle = t.originalTitle,
            originalLanguage = t.originalLanguage
        )
    }
}