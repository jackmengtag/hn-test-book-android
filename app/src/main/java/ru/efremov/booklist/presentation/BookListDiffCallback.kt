package ru.efremov.booklist.presentation

import androidx.recyclerview.widget.DiffUtil
import ru.efremov.booklist.domain.BookItem

class BookListDiffCallback(
    private val oldList: List<BookItem>,
    private val newList: List<BookItem>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}