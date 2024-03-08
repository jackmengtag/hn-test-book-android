package ru.efremov.booklist.domain

import androidx.lifecycle.LiveData
import javax.inject.Inject

class GetBookListUseCase @Inject constructor(
    private val bookListRepository: BookListRepository
) {

    fun getBookList(): LiveData<List<BookItem>> {
        return bookListRepository.getBookList()
    }
}
