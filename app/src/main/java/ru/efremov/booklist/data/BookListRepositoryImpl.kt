package ru.efremov.booklist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import ru.efremov.booklist.domain.BookItem
import ru.efremov.booklist.domain.BookListRepository
import javax.inject.Inject

class BookListRepositoryImpl @Inject constructor(
    private val bookListDao: BookListDao,
    private val mapper: BookListMapper
): BookListRepository {

    override suspend fun addBookItem(bookItem: BookItem) {
        bookListDao.addBookItem(mapper.mapEntityToDbModel(bookItem))
    }

    override suspend  fun deleteBookItem(bookItem: BookItem) {
        bookListDao.deleteBookItem(bookItem.id)
    }

    override suspend  fun editBookItem(bookItem: BookItem) {
        bookListDao.addBookItem(mapper.mapEntityToDbModel(bookItem))
    }

    override suspend  fun getBookItem(bookItemId: String): BookItem {
        val dbModel = bookListDao.getBookItem(bookItemId)
        return mapper.mapDbModelToEntity(dbModel)
    }

    override fun getBookList(): LiveData<List<BookItem>> = Transformations.map(
        bookListDao.getBookList())
    {
        mapper.mapListDbModelToListEntity(it)
    }
//    override fun getBookList(): LiveData<List<BookItem>> = MediatorLiveData<List<BookItem>>().apply {
//        addSource(bookListDao.getBookList()) {
//            value = mapper.mapListDbModelToListEntity(it)
//        }
//    }
}