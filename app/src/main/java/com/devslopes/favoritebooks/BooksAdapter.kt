package com.devslopes.favoritebooks

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devslopes.favoritebooks.models.Book
import kotlinx.android.synthetic.main.item_book.view.*

class BooksAdapter(
    val booksList: List<Book>, context: Context) : RecyclerView.Adapter<BooksAdapter.BookViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return BookViewHolder(layoutInflater.inflate(R.layout.item_book, parent,false))
    }

    override fun getItemCount(): Int = booksList.size

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.onBind(booksList[position])
    }

    fun deleteItem(position:Int, context: Context) {
        BookRepository.removeBook(booksList[position], context )
    }

    class BookViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        fun onBind(book: Book) {
            itemView.apply {
                title__item_label.text = book.title
                author_item_label.text = book.author
                genre_item_label.text = book.genre
                length_item_label.text = book.length
            }

        }
    }
}