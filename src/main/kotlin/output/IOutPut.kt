package output

import book.Book

interface IOutPut {
    fun showMessage(message:String, lineBreak:Boolean = true)
    fun show(bookList:List<Book>?, message: String = "All books:")
}