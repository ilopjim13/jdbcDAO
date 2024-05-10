import book.Book
import dao.BookDAO
import datasource.DataSourceFactory
import output.Console
import service.BookService


fun main() {

    val console = Console()

    // Creamos la instancia de la base de datos
    val dataSource = DataSourceFactory.getDS(DataSourceFactory.DataSourceType.HIKARI)

    // Creamos la instancia de UserDAO
    val userDao = BookDAO(dataSource, console)

    // Creamos la instancia de UserService
    val userService = BookService(userDao)

    // Creamos un nuevo usuario
    val newUser = Book(name = "John Doe", author = "johndoe@example.com", publicYear = 2000, theme = "theme")
    var createdUser = userService.create(newUser)

    console.showMessage("Created user: ${createdUser ?: "error"}")



    // Obtenemos un usuario por su ID
    val foundUser =
        if (createdUser != null )userService.getById(createdUser.id)
        else null
    console.showMessage("Found user: ${foundUser ?: "error"}")

    // Actualizamos el usuario
    val updatedUser = foundUser?.copy(name = "Jane Doe")
    val savedUser =
        if (updatedUser != null) userService.update(updatedUser)
        else null
    console.showMessage("Updated user: $${savedUser ?: "error"}")

    val otherUser = Book(name = "Eduardo Fernandez", author = "eferoli@gmail.com", publicYear = 2000, theme = "theme")
    createdUser = userService.create( otherUser)
    console.showMessage("Created user: $${createdUser ?: "error"}")


    // Obtenemos todos los usuarios
    var allUsers = userService.getAll()
    console.show(allUsers)

    // Eliminamos el usuario
    if (savedUser != null) {
        if (userService.delete(savedUser.id)) console.showMessage("User deleted OK!")
        else console.showMessage("User deleted error!")
    }

    // Obtenemos todos los usuarios
    allUsers = userService.getAll()
    console.show(allUsers)

    // Eliminamos el usuario

    if (userService.delete(otherUser.id)) console.showMessage("User deleted OK!")
    else console.showMessage("User deleted error!")

    // Obtenemos todos los usuarios
    allUsers = userService.getAll()
    console.show(allUsers)

}