package book

import utilities.Utilities
import java.util.*

data class Book(
    val id: UUID = Utilities.generateUUID(),
    val name:String,
    val author:String,
    val publicYear:Int,
    val theme:String
)
