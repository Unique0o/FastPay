package com.sampleandroidapp.data.entities

import com.sampleandroidapp.domain.entities.MovieEntity
import com.google.gson.annotations.SerializedName

/**
 * Created by Ali Asadi on 13/05/2020
 */
data class MovieData(
    @SerializedName("id") val id: Int,
    @SerializedName("description") val description: String,
    @SerializedName("image") val image: String,
    @SerializedName("title") val title: String,
    @SerializedName("category") val category: String,
)

fun MovieData.toDomain() = MovieEntity(
    id = id,
    image = image,
    description = description,
    title = title,
    category = category
)