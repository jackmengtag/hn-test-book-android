package ru.efremov.booklist.data

import ru.efremov.booklist.domain.BookItem
import javax.inject.Inject

class BookListMapper @Inject constructor() {

    fun mapEntityToDbModel(bookItem: BookItem) = BookItemDbModel(
        id = bookItem.id,
        title = bookItem.title,
        bookname = bookItem.bookname,
        author = bookItem.author,
        publishtime = bookItem.publishtime,
        sbn = bookItem.sbn,
        enabled = bookItem.enabled
    )

    fun mapDbModelToEntity(bookItemDbModel: BookItemDbModel) = BookItem(
        id = bookItemDbModel.id,
        title = bookItemDbModel.title,
        bookname = bookItemDbModel.bookname,
        author = bookItemDbModel.author,
        publishtime = bookItemDbModel.publishtime,
        sbn = bookItemDbModel.sbn,
        enabled = bookItemDbModel.enabled
    )

    fun mapListDbModelToListEntity(list: List<BookItemDbModel>) = list.map {
        mapDbModelToEntity(it)
    }
}