package ru.efremov.booklist.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import ru.efremov.booklist.presentation.MainActivity
import ru.efremov.booklist.presentation.BookItemFragment

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun inject(activity: MainActivity)
    fun inject(fragment: BookItemFragment)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}