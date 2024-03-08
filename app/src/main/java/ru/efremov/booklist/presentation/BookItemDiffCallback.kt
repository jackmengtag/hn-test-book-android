package ru.efremov.booklist.presentation

import androidx.recyclerview.widget.DiffUtil
import ru.efremov.booklist.domain.BookItem

class BookItemDiffCallback : DiffUtil.ItemCallback<BookItem>() {

    override fun areItemsTheSame(oldItem: BookItem, newItem: BookItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: BookItem, newItem: BookItem): Boolean {
        return oldItem == newItem
    }
}