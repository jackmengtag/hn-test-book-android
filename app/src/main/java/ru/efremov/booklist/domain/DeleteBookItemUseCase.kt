package ru.efremov.booklist.domain

import javax.inject.Inject

class DeleteBookItemUseCase @Inject constructor(
    private val bookListRepository: BookListRepository
) {

    suspend fun deleteBookItem(bookItem: BookItem) {
        bookListRepository.deleteBookItem(bookItem)
    }
}