import book.Book
import dao.BookDAO
import dao.BookFileDAO
import datasource.DataSourceFactory
import fileManager.FileManager
import output.Console
import service.BookService
import utilities.Utilities
import java.io.File


fun main() {

    val console = Console()



    var choose = Utilities.typeChoose(console)

    // Creamos la instancia de la base de datos
    val dataSource = DataSourceFactory.getDS(DataSourceFactory.DataSourceType.HIKARI)

    // Creamos la instancia de UserDAO
    val bookDao =
        if (choose) {
            BookDAO(dataSource, console)
        } else {
            val fileDAO = FileManager()
            BookFileDAO(fileDAO, File(""))
        }



    // Creamos la instancia de UserService
    val bookService = BookService(bookDao)

    // Creamos un nuevo usuario
    val newBook = Book(name = "John Doe", author = "johndoe@example.com", publicYear = 2000, theme = "theme")
    var createdBook = bookService.create(newBook)

    console.showMessage("Created book: ${createdBook ?: "error"}")



    // Obtenemos un usuario por su ID
    val foundBook =
        if (createdBook != null )bookService.getById(createdBook.id)
        else null
    console.showMessage("Found book: ${foundBook ?: "error"}")

    // Actualizamos el usuario
    val updatedBook = foundBook?.copy(name = "Jane Doe")
    val savedBook =
        if (updatedBook != null) bookService.update(updatedBook)
        else null
    console.showMessage("Updated book: $${savedBook ?: "error"}")

    val otherBook = Book(name = "Eduardo Fernandez", author = "eferoli@gmail.com", publicYear = 2000, theme = "theme")
    createdBook = bookService.create( otherBook)
    console.showMessage("Created book: $${createdBook ?: "error"}")


    // Obtenemos todos los usuarios
    var allBooks = bookService.getAll()
    console.show(allBooks)

    // Eliminamos el usuario
    if (savedBook != null) {
        if (bookService.delete(savedBook.id)) console.showMessage("Book deleted OK!")
        else console.showMessage("Book deleted error!")
    }

    // Obtenemos todos los usuarios
    allBooks = bookService.getAll()
    console.show(allBooks)

    // Eliminamos el usuario

    if (bookService.delete(otherBook.id)) console.showMessage("Book deleted OK!")
    else console.showMessage("Book deleted error!")

    // Obtenemos todos los usuarios
    allBooks = bookService.getAll()
    console.show(allBooks)

}