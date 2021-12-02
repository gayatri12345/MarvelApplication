package com.sample.domain.model

/**
 * Data class for Marvel Character
 */
data class MarvelCharacter(
    val id: Int,
    val name: String,
    val description: String,
    val modified: String,
    val thumbnail: String
)