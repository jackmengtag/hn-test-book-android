package ru.efremov.booklist.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
//import ru.efremov.booklist.R
//import ru.efremov.booklist.databinding.ItemBookDisabledBinding
//import ru.efremov.booklist.databinding.ItemBookEnabledBinding
import ru.efremov.booklist.domain.BookItem
import ru.efremov.booklist.R
import ru.efremov.booklist.databinding.ItemBookDisabledBinding
import ru.efremov.booklist.databinding.ItemBookEnabledBinding

class BookListAdapter : ListAdapter<BookItem, BookItemViewHolder>(BookItemDiffCallback()) {

    var onBookItemLongClickListener: ((BookItem) -> Unit)? = null
    var onBookItemClickListener: ((BookItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookItemViewHolder {
        val layout = when (viewType) {
            VIEW_TYPE_DISABLED -> R.layout.item_book_disabled
            VIEW_TYPE_ENABLED -> R.layout.item_book_enabled
            else -> throw RuntimeException("Unknown viewType: $viewType")
        }
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            layout,
            parent,
            false
        )
        return BookItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookItemViewHolder, position: Int) {
        val bookItem = getItem(position)
        val binding = holder.binding
        when (binding) {
            is ItemBookDisabledBinding -> {
                binding.bookItem = bookItem
            }
            is ItemBookEnabledBinding -> {
                binding.bookItem = bookItem
            }
        }
        binding.root.setOnLongClickListener {
            onBookItemLongClickListener?.invoke(bookItem)
            true
        }
        binding.root.setOnClickListener {
            onBookItemClickListener?.invoke(bookItem)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item.enabled) {
            VIEW_TYPE_ENABLED
        } else {
            VIEW_TYPE_DISABLED
        }
    }

    companion object {

        const val VIEW_TYPE_ENABLED = 100
        const val VIEW_TYPE_DISABLED = 101

        const val MAX_POOL_SIZE = 15
    }
}