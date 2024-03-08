package ru.efremov.booklist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.efremov.booklist.domain.DeleteBookItemUseCase
import ru.efremov.booklist.domain.EditBookItemUseCase
import ru.efremov.booklist.domain.GetBookListUseCase
import ru.efremov.booklist.domain.BookItem
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getBookListUseCase: GetBookListUseCase,
    private val deleteBookItemUseCase: DeleteBookItemUseCase,
    private val editBookItemUseCase: EditBookItemUseCase
) : ViewModel() {

    val bookList = getBookListUseCase.getBookList()

//    private val scope = CoroutineScope(Dispatchers.IO)

    fun deleteBookItem(bookItem: BookItem) {
        viewModelScope.launch {
            deleteBookItemUseCase.deleteBookItem(bookItem)
        }
    }

    fun changeEnableState(bookItem: BookItem) {
        viewModelScope.launch {
            val newItem = bookItem.copy(enabled = !bookItem.enabled)
            editBookItemUseCase.editBookItem(newItem)
        }
    }

//    override fun onCleared() {
//        super.onCleared()
//        scope.cancel()
//    }
}