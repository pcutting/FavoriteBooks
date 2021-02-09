package com.devslopes.favoritebooks

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devslopes.favoritebooks.databinding.ActivityMainBinding
import com.devslopes.favoritebooks.models.Book

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate")
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val booksList : MutableList<Book> = BookRepository.getBooks(this)

        val bAdapter = BooksAdapter(booksList, this)

        binding.bookList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            adapter = bAdapter
            //addItemDecoration(SimpleDividerItemDecoration(this))
//            addOnScrollListener( OnScrollListener{
//              override fun onScrolled(recycerView: RecyclerView, dx : Int, dy : Int){
//                  if (dy > 0 || dy < 0 && binding.fab.isVisible) {
//                      binding.fab.hide()
//                  }
//              }
//            })

            binding.bookList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (dy > 0 || dy < 0 && binding.fab.isShown) {
                        binding.fab.hide()
                    }
                }

                override fun onScrollStateChanged(
                    recyclerView: RecyclerView,
                    newState: Int
                ) {
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        binding.fab.show()
                    }
                    super.onScrollStateChanged(recyclerView, newState)
                }
            })
                //----------


        }

        val swipeToDeleteCallback = object : SwipeToDeleteCallback() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val pos = viewHolder.adapterPosition
                booksList.removeAt(pos)
                bAdapter.notifyItemRemoved(pos)
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(binding.bookList)

        binding.fab.setOnClickListener {
            Log.d(TAG, "fab clicked")
            loadBookForm(this, binding)
            //adapter.notifyDataSetChanged()
            bAdapter.notifyDataSetChanged()

        }
    }

    private fun loadBookForm(context: Context, binding: ActivityMainBinding) {
        val intent = Intent(context, FormActivity::class.java)
            .apply {
            putExtra("New Book", "Book form")
        }
        //binding.bookList.adapter?.notifyDataSetChanged()
        startActivity(intent)
    }
}
