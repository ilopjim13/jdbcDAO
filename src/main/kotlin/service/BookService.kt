package service

import book.Book
import dao.IBookDAO
import java.util.*

class BookService(private val bookDAO: IBookDAO):IBookService {
    override fun create(book: Book): Book? {
        return bookDAO.create(book)
    }

    override fun getAll(): List<Book>? {
        return bookDAO.getAll()
    }

    override fun getById(id: UUID): Book? {
        return bookDAO.getById(id)
    }

    override fun update(book: Book): Book? {
        return bookDAO.update(book)
    }

    override fun delete(id: UUID): Boolean {
        return bookDAO.delete(id)
    }

}
