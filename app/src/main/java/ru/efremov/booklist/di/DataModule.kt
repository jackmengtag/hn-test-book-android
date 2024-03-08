package ru.efremov.booklist.di

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.efremov.booklist.data.AppDatabase
import ru.efremov.booklist.data.BookListDao
import ru.efremov.booklist.data.BookListRepositoryImpl
import ru.efremov.booklist.domain.BookListRepository

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindBookListRepository(impl: BookListRepositoryImpl): BookListRepository

    companion object {

        @ApplicationScope
        @Provides
        fun provideBookListDao(
            application: Application
        ): BookListDao {
            return AppDatabase.getInstance(application).bookListDao()
        }
    }
}