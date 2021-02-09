package com.devslopes.favoritebooks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.devslopes.favoritebooks.databinding.ActivityFormBinding
import com.devslopes.favoritebooks.models.Book

class FormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //throw RuntimeException("testing firebase crashlytics")

        binding.saveBookButton.setOnClickListener {

            val book = Book(
                author = binding.authorInput.text.toString() ,
                title = binding.titleInput.text.toString(),
                length = binding.lengthInput.text.toString(),
                genre = binding.genreInput.text.toString()
            )

            BookRepository.addBook(book, this)
            //AdapterListUpdateCallback(BooksAdapter)
            //this.parent.book_list.adapter?.notifyDataSetChanged()

            onBackPressed()
        }
    }
}