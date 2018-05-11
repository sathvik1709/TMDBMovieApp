package com.sathvik1709.tmdbsamsungtest.dto


data class GenreResponse(
		val genres: List<Genre>
)

data class Genre(
		val id: Int,
		val name: String
)