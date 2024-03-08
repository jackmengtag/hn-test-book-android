package ru.efremov.booklist.domain

import javax.inject.Inject

class EditBookItemUseCase @Inject constructor(
    private val bookListRepository: BookListRepository
) {

    suspend fun editBookItem(bookItem: BookItem) {
        bookListRepository.editBookItem(bookItem)
    }
}