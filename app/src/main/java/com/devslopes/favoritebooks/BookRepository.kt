package com.devslopes.favoritebooks

import android.content.Context
import com.devslopes.favoritebooks.models.Book

object BookRepository {

    private const val SEPARATOR = "|"
    private const val BOOKS_KEY = "BOOKS_KEY"
    private const val NAME = "FavoriteBooks"
    private val books = mutableListOf<Book>()

    fun getBooks(context: Context): List<Book> {
        if (books.isEmpty()) {
            val sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE)
            val booksString = sharedPreferences.getString(BOOKS_KEY, "").orEmpty()

            if (!booksString.isNullOrEmpty()) {
                val tempBooks = booksString.split(SEPARATOR)
                    .map { Book.fromCsv(it) }

                books.addAll(tempBooks)
            }
        }

        return books.toList()
    }

    fun addBook(book: Book, context: Context) {
        val added = books.add(book)

        if (added) {
            saveBookList(context)
        }
    }

    fun removeBook(book: Book, context: Context) {
        val removed = books.remove(book)

        if (removed) {
            saveBookList(context)
        }
    }

    private fun saveBookList(context: Context) {
        val booksString = books.joinToString(SEPARATOR) { it.toCsv() }
        val sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE)
        sharedPreferences.edit()
            .putString(BOOKS_KEY, booksString)
            .apply()
    }

}