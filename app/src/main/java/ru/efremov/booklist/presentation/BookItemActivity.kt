package ru.efremov.booklist.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
//import ru.efremov.booklist.R
import ru.efremov.booklist.domain.BookItem
import ru.efremov.booklist.R

class BookItemActivity : AppCompatActivity(), BookItemFragment.OnEditingFinishedListener {

    private var screenMode = MODE_UNKNOWN
    private var bookItemId = MODE_UNKNOWN

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_item)
        parseIntent()
        if (savedInstanceState == null) {
            launchRightMode()
        }
    }

    override fun onEditingFinished() {
        finish()
    }

    private fun launchRightMode() {
        val fragment = when (screenMode) {
            MODE_EDIT -> BookItemFragment.newInstanceEditItem(bookItemId)
            MODE_ADD  -> BookItemFragment.newInstanceAddItem()
            else      -> throw RuntimeException("Unknown screen mode $screenMode")
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.book_item_container, fragment)
            .commit()
    }

    private fun parseIntent() {
        if (!intent.hasExtra(EXTRA_SCREEN_MODE)) {
            throw RuntimeException("Param screen_mode is absent")
        }
        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
        if (mode != MODE_EDIT && mode != MODE_ADD) {
            throw RuntimeException("Unknown screen mode $mode")
        }
        screenMode = mode
        if (screenMode == MODE_EDIT) {
            if (!intent.hasExtra(EXTRA_BOOK_ITEM_ID)) {
                throw RuntimeException("Param book_item_id is absent")
            }
            var  itemId = intent.getStringExtra(EXTRA_BOOK_ITEM_ID)
            if (itemId != MODE_EDIT && mode != MODE_ADD) {
                throw RuntimeException("Unknown screen itemId $mode")
            }
//            bookItemId = itemId
        }
    }

    companion object {

        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val EXTRA_BOOK_ITEM_ID = "extra_book_item_id"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"

        private const val MODE_UNKNOWN = ""

        fun newIntentAddItem(context: Context): Intent {
            val intent = Intent(context, BookItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
            return intent
        }

        fun newIntentEditItem(context: Context, bookItemId: String): Intent {
            val intent = Intent(context, BookItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
            intent.putExtra(EXTRA_BOOK_ITEM_ID, bookItemId)
            return intent
        }
    }
}