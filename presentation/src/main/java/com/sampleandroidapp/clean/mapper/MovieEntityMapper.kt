package com.sampleandroidapp.clean.mapper

import com.sampleandroidapp.clean.entities.MovieListItem
import com.sampleandroidapp.domain.entities.MovieEntity

/**
 * @author by Ali Asadi on 26/08/2022
 */

fun MovieEntity.toPresentation() = MovieListItem.Movie(
    id = id,
    imageUrl = image,
    category = category
)