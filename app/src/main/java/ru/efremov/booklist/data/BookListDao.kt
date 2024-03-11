package ru.efremov.booklist.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BookListDao {

    @Query("SELECT * FROM book_items")
    fun getBookList(): LiveData<List<BookItemDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBookItem(bookItemDbModel: BookItemDbModel)

    @Query("DELETE FROM book_items WHERE id=:bookItemId")
    suspend fun deleteBookItem(bookItemId: String)

    @Query("SELECT * FROM book_items WHERE id=:bookItemId LIMIT 1")
    suspend fun getBookItem(bookItemId: String): BookItemDbModel
}