package service

import book.Book
import java.util.*

interface IBookService {
    fun create(book: Book): Book?
    fun getAll(): List<Book>?
    fun getById(id: UUID): Book?
    fun update(book: Book): Book?
    fun delete(id: UUID): Boolean
}