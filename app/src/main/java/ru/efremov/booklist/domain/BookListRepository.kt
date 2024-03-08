package ru.efremov.booklist.domain

import androidx.lifecycle.LiveData

interface BookListRepository {

    suspend fun addBookItem(bookItem: BookItem)

    suspend fun deleteBookItem(bookItem: BookItem)

    suspend fun editBookItem(bookItem: BookItem)

    suspend fun getBookItem(bookItemId: String): BookItem

    fun getBookList(): LiveData<List<BookItem>>
}