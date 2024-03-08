package ru.efremov.booklist.domain

import javax.inject.Inject

class AddBookItemUseCase @Inject constructor(
    private val bookListRepository: BookListRepository
) {

    suspend fun addBookItem(bookItem: BookItem) {
        bookListRepository.addBookItem(bookItem)
    }
}