package com.sampleandroidapp.data.mapper

import com.sampleandroidapp.data.entities.MovieDbData
import com.sampleandroidapp.domain.entities.MovieEntity

/**
 * Created by Ali Asadi on 13/05/2020
 **/

fun MovieEntity.toDbData() = MovieDbData(
    id = id,
    image = image,
    description = description,
    title = title,
    category = category
)
