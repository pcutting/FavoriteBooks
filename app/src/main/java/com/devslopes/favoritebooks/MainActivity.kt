package com.devslopes.favoritebooks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.devslopes.favoritebooks.databinding.ActivityMainBinding
import com.devslopes.favoritebooks.models.Book

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bookList.apply {
            adapter = BooksAdapter(BookRepository.getBooks(this@MainActivity))
            //item
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }
}
