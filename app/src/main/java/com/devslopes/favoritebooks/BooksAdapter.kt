package com.devslopes.favoritebooks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devslopes.favoritebooks.databinding.ItemBookBinding
import com.devslopes.favoritebooks.models.Book

class BooksAdapter(
    private val booksList: List<Book>
) : RecyclerView.Adapter<BooksAdapter.BookViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBookBinding.inflate(inflater)
        return BookViewHolder(binding)
    }

    override fun getItemCount(): Int = booksList.size

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.onBind(booksList[position])
    }

    class BookViewHolder(
        private val binding: ItemBookBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(book: Book) = binding.apply {
            title.text = book.title
            subtitle.text = book.author
        }
    }
}