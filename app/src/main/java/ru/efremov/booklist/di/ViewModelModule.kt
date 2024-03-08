package ru.efremov.booklist.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.efremov.booklist.presentation.MainViewModel
import ru.efremov.booklist.presentation.BookItemViewModel

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BookItemViewModel::class)
    fun bindBookItemViewModel(viewModel: BookItemViewModel): ViewModel
}