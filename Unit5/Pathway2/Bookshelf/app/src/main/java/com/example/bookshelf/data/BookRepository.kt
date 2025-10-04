package com.example.bookshelf.data

import com.example.bookshelf.model.Book
import com.example.bookshelf.network.BooksApiService
import com.google.gson.Gson

interface BooksRepository {
    suspend fun getBooks(query: String): List<Book>
}

class DefaultBooksRepository(
    private val booksApiService: BooksApiService
) : BooksRepository {
    override suspend fun getBooks(query: String): List<Book> {
        return booksApiService.getVolumes(query = query).items
    }
}