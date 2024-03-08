package ru.efremov.booklist.domain

import javax.inject.Inject

class GetBookItemUseCase @Inject constructor(
    private val bookListRepository: BookListRepository
) {

    suspend fun getBookItem(bookItemId: String): BookItem {
        return bookListRepository.getBookItem(bookItemId)
    }
}