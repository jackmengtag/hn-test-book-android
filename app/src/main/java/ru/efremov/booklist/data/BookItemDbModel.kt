package ru.efremov.booklist.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book_items")
data class BookItemDbModel(
    @PrimaryKey()
    val id: String,
    val title: String,
    val author: String,
    val sbn: String,
    val bookname: String,   //书名
    val publishtime: String,   //出版年份
    val enabled: Boolean
)