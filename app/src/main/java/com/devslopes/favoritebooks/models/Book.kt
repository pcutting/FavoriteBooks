package com.devslopes.favoritebooks.models

data class Book(
    val title: String = "title",
    val author: String = "author",
    val genre: String = "genre",
    val length: String = "length"
) {

    companion object {
        fun fromCsv(csv: String): Book {
            val items = csv.split(",")
            return Book(
                title = items[0],
                author = items[1],
                genre = items[2],
                length = items[3]
            )
        }
    }

    val description: String
        get() = "$title is written by $author in the $genre genre and is $length pages long"

    fun toCsv(): String = "$title,$author,$genre,$length"
}