package com.example.bookshelf.model

import kotlinx.serialization.Serializable

data class BookList(
    @Serializable
    val items: List<Book>
)

data class Book(
    val id: String,
    val volumeInfo: VolumeInfo
)

@Serializable
data class VolumeInfo (
    val title: String,
    val authors: List<String>,
    val publisher: String,
    val publishedDate: String,
    val description: String,
    val imageLinks: ImageLinks,
)

@Serializable
data class ImageLinks (
    val smallThumbnail: String,
    val thumbnail: String,
)
