package ru.efremov.booklist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.efremov.booklist.domain.AddBookItemUseCase
import ru.efremov.booklist.domain.EditBookItemUseCase
import ru.efremov.booklist.domain.GetBookItemUseCase
import ru.efremov.booklist.domain.BookItem
import javax.inject.Inject

class BookItemViewModel @Inject constructor(
    private val getBookItemUserCase: GetBookItemUseCase,
    private val addBookItemUserCase: AddBookItemUseCase,
    private val editBookItemUserCase: EditBookItemUseCase
) : ViewModel() {

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    private val _errorInputAuthor = MutableLiveData<Boolean>()
    val errorInputAuthor: LiveData<Boolean>
        get() = _errorInputAuthor

    private val _errorInputTitle = MutableLiveData<Boolean>()
    val errorInputTitle: LiveData<Boolean>
        get() = _errorInputTitle

    private val _errorInputSbn = MutableLiveData<Boolean>()
    val errorInputSbn: LiveData<Boolean>
        get() = _errorInputSbn

    private val _errorInputPublishtime = MutableLiveData<Boolean>()
    val errorInputPublishtime: LiveData<Boolean>
        get() = _errorInputPublishtime


    private val _bookItem = MutableLiveData<BookItem>()
    val bookItem: LiveData<BookItem>
        get() = _bookItem

    private val _shouldCloseScreen = MutableLiveData<Unit>()
    val shouldCloseScreen: LiveData<Unit>
        get() = _shouldCloseScreen

    fun getBookItem(bookItemId: String) {
        viewModelScope.launch {
            val item = getBookItemUserCase.getBookItem(bookItemId)
            _bookItem.postValue(item)
        }
    }

    fun addBookItem(inputName: String?, inputAuthor: String?, inputsbn: String?, inputTitle: String?, inputPublishtime: String?) {
        val name = parseName(inputName)
        val author = parseAuthor(inputAuthor)
        val sbn = parseAuthor(inputsbn)
        val title = parseAuthor(inputTitle)
        val publishtime = parseAuthor(inputPublishtime)
        val fieldsValid = validateInput(name, author)
        if (fieldsValid) {
            viewModelScope.launch {
                val bookItem = BookItem(title, author, sbn,name,publishtime,"",true)
                addBookItemUserCase.addBookItem(bookItem)
                finishWork()
            }
        }
    }

    fun editBookItem(inputName: String?, inputAuthor: String?) {
        val name = parseName(inputName)
        val author = parseAuthor(inputAuthor)
        val fieldsValid = validateInput(name, author)
        if (fieldsValid) {
            _bookItem.value?.let {
                viewModelScope.launch {
                    val item = it.copy(bookname = name, author = author)
                    editBookItemUserCase.editBookItem(item)
                    finishWork()
                }
            }
        }
    }

    private fun parseName(inputName: String?): String {
        return inputName?.trim() ?: ""
    }

    private fun parseAuthor(inputAuthor: String?): String {
        return inputAuthor?.trim() ?: ""
    }

    private fun parseTitle(inputTitle: String?): String {
        return inputTitle?.trim() ?: ""
    }

    private fun parseSbn(inputSbn: String?): String {
        return inputSbn?.trim() ?: ""
    }

    private fun parsePublishTime(inputPublishTime: String?): String {
        return inputPublishTime?.trim() ?: ""
    }

    private fun validateInput(name: String, author: String): Boolean {
        var result = true
        if (name.isBlank()) {
            _errorInputName.value = true
            result = false
        }
        if (author.isBlank()) {
            _errorInputAuthor.value = true
            result = false
        }
        return result
    }

    fun resetErrorInputName() {
        _errorInputName.value = false
    }

    fun resetErrorInputAuthor() {
        _errorInputAuthor.value = false
    }

    private fun finishWork() {
        _shouldCloseScreen.postValue(Unit)
    }

//    override fun onCleared() {
//        super.onCleared()
//        scope.cancel()
//    }
}