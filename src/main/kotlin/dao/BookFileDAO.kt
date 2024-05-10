package dao

import book.Book
import fileManager.FileManager
import java.io.File
import java.util.*

class BookFileDAO(private val fileManager: FileManager, private val file:File) :IBookDAO {
    override fun create(book: Book): Book? {
        TODO("Not yet implemented")
    }

    override fun getAll(): List<Book>? {
        TODO("Not yet implemented")
    }

    override fun getById(id: UUID): Book? {
        TODO("Not yet implemented")
    }

    override fun update(book: Book): Book? {
        TODO("Not yet implemented")
    }

    override fun delete(id: UUID): Boolean {
        TODO("Not yet implemented")
    }
}