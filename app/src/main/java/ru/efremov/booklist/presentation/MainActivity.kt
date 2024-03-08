package ru.efremov.booklist.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
//import ru.efremov.booklist.R
import ru.efremov.booklist.BookListApp
//import ru.efremov.booklist.databinding.ActivityMainBinding
import ru.efremov.booklist.R
import ru.efremov.booklist.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : AppCompatActivity(), BookItemFragment.OnEditingFinishedListener {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (application as BookListApp).component
    }

    private lateinit var viewModel: MainViewModel

    private lateinit var bookListAdapter: BookListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        viewModel.bookList.observe(this) {
            bookListAdapter.submitList(it)
        }

        binding.buttonAddBookItem.setOnClickListener {
            if (isOnePaneMode()) {
                val intent = BookItemActivity.newIntentAddItem(this)
                startActivity(intent)
            } else {
                launchFragment(BookItemFragment.newInstanceAddItem())
            }
        }
    }

    override fun onEditingFinished() {
        Toast.makeText(this@MainActivity, "Success", Toast.LENGTH_SHORT).show()
        supportFragmentManager.popBackStack()
    }

    private fun isOnePaneMode(): Boolean {
        return binding.bookItemContainer == null
    }

    private fun launchFragment(fragment: Fragment) {
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(R.id.book_item_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun setupRecyclerView() {
        with(binding.rvBookList) {
            bookListAdapter = BookListAdapter()
            adapter = bookListAdapter
            recycledViewPool.setMaxRecycledViews(
                BookListAdapter.VIEW_TYPE_ENABLED,
                BookListAdapter.MAX_POOL_SIZE
            )
            recycledViewPool.setMaxRecycledViews(
                BookListAdapter.VIEW_TYPE_DISABLED,
                BookListAdapter.MAX_POOL_SIZE
            )
        }
        setupLongClickListener()
        setupClickListener()
        setupSwipeListener(binding.rvBookList)
    }

    private fun setupClickListener() {
        bookListAdapter.onBookItemClickListener = {
            if (isOnePaneMode()) {
                val intent = BookItemActivity.newIntentEditItem(this, it.id)
                startActivity(intent)
            } else {
                launchFragment(BookItemFragment.newInstanceEditItem(it.id))
            }
        }
    }

    private fun setupLongClickListener() {
        bookListAdapter.onBookItemLongClickListener = {
            viewModel.changeEnableState(it)
        }
    }

    private fun setupSwipeListener(rvBookList: RecyclerView) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = bookListAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteBookItem(item)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvBookList)
    }
}