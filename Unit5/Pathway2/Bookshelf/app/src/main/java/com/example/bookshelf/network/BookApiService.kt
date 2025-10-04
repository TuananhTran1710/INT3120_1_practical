package com.example.bookshelf.network

import com.example.bookshelf.model.BookList
import retrofit2.http.GET
import retrofit2.http.Query

interface BooksApiService {
    @GET("volumes")
    suspend fun getVolumes(@Query("q") query: String): BookList
}