package com.sathvik1709.tmdbsamsungtest.dto

import android.os.Parcel
import android.os.Parcelable


data class MoviesListResponse(
        val results: List<Movie>,
        val page: Int,
        val total_results: Int,
        val dates: Dates,
        val total_pages: Int
)

data class Dates(
        val maximum: String,
        val minimum: String
)

// Has Parcelable implementation so the class looks BIG :)
data class Movie(
        val vote_count: Int,
        val id: Int,
        val video: Boolean,
        val vote_average: Double,
        val title: String,
        val popularity: Double,
        val poster_path: String,
        val original_language: String,
        val original_title: String,
        val genre_ids: List<Int>,
        var genre_names: List<String>,
        val backdrop_path: String,
        val adult: Boolean,
        val overview: String,
        val release_date: String
) : Parcelable {
    constructor(source: Parcel) : this(
            source.readInt(),
            source.readInt(),
            1 == source.readInt(),
            source.readDouble(),
            source.readString(),
            source.readDouble(),
            source.readString(),
            source.readString(),
            source.readString(),
            ArrayList<Int>().apply { source.readList(this, Int::class.java.classLoader) },
            source.createStringArrayList(),
            source.readString(),
            1 == source.readInt(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(vote_count)
        writeInt(id)
        writeInt((if (video) 1 else 0))
        writeDouble(vote_average)
        writeString(title)
        writeDouble(popularity)
        writeString(poster_path)
        writeString(original_language)
        writeString(original_title)
        writeList(genre_ids)
        writeStringList(genre_names)
        writeString(backdrop_path)
        writeInt((if (adult) 1 else 0))
        writeString(overview)
        writeString(release_date)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Movie> = object : Parcelable.Creator<Movie> {
            override fun createFromParcel(source: Parcel): Movie = Movie(source)
            override fun newArray(size: Int): Array<Movie?> = arrayOfNulls(size)
        }
    }
}