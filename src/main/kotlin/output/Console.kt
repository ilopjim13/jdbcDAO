package output

import book.Book

class Console:IOutPut {
    override fun showMessage(message: String, lineBreak: Boolean) {
        if (lineBreak) println(message)
        else print(message)
    }

    override fun show(bookList: List<Book>?, message: String) {
        if (bookList != null) {
            if (bookList.isNotEmpty()) {
                showMessage(message)
                bookList.forEachIndexed { index, user ->
                    showMessage("\t${index + 1}. $user")
                }
            } else {
                showMessage("No users found!")
            }
        }
    }
}