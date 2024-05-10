package dao

import book.Book
import output.Console
import java.sql.SQLException
import java.util.*
import javax.sql.DataSource

class BookDAO(private val dataSource: DataSource, private val console: Console): IBookDAO {
    override fun create(book: Book): Book? {
        val sql = "INSERT INTO books (id, name, author, publicYear, theme) VALUES (?, ?, ?, ?, ?)"
        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setString(1, book.id.toString())
                    stmt.setString(2, book.name)
                    stmt.setString(3, book.author)
                    stmt.setString(4, book.publicYear.toString())
                    stmt.setString(5, book.theme)
                    val rs = stmt.executeUpdate()
                    if (rs == 1) {
                        book
                    }else{
                        console.showMessage("**ERROR** insert query failed! ($rs records inserted)")
                        null
                    }
                }
            }
        } catch (e: SQLException) {
            console.showMessage("**ERROR** Insert query failed! (${e.message})")
            null
        }
    }

    override fun getAll(): List<Book>? {
        val sql = "SELECT * FROM BOOKS"
        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    val rs = stmt.executeQuery()
                    val users = mutableListOf<Book>()
                    while (rs.next()) {
                        users.add(
                            Book(
                                id = UUID.fromString(rs.getString("id")),
                                name = rs.getString("name"),
                                author = rs.getString("author"),
                                publicYear = rs.getInt("publicYear"),
                                theme = rs.getString("theme")
                            )
                        )
                    }
                    users
                }
            }
        }catch (e:SQLException) {
            console.showMessage("**ERROR** Select query failed! (${e.message})")
            null
        }
    }

    override fun getById(id: UUID): Book? {
        val sql = "SELECT * FROM BOOKS WHERE id = ?"
        return try{
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setString(1, id.toString())
                    val rs = stmt.executeQuery()
                    if (rs.next()) {
                        Book(
                            id = UUID.fromString(rs.getString("id")),
                            name = rs.getString("name"),
                            author = rs.getString("author"),
                            publicYear = rs.getInt("publicYear"),
                            theme = rs.getString("theme")
                        )
                    } else {
                        null
                    }
                }
            }
        } catch (e:SQLException) {
            console.showMessage("**ERROR** Select query failed! (${e.message})")
            null
        }
    }

    override fun update(book: Book): Book? {
        val sql = "UPDATE BOOKS SET name = ?, author = ? WHERE id = ?"
        return try{
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setString(1, book.name)
                    stmt.setString(2, book.author)
                    stmt.setString(3, book.id.toString())
                    val rs = stmt.executeUpdate()
                    if (rs == 1) {
                        book
                    }else{
                        console.showMessage("**ERROR** update query failed! ($rs records updated)")
                        null
                    }
                }
            }
        }catch (e:SQLException) {
            console.showMessage("**ERROR** Select query failed! (${e.message})")
            null
        }
    }

    override fun delete(id: UUID): Boolean {
        val sql = "DELETE FROM BOOKS WHERE id = ?"
        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setString(1, id.toString())
                    (stmt.executeUpdate() == 1)
                }
            }
        }catch (e:SQLException) {
            console.showMessage("**ERROR** Select query failed! (${e.message})")
            false
        }
    }
}