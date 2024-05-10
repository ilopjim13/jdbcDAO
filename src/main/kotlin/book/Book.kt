package book

import java.util.*

data class Book(
    val id: UUID = UUID.randomUUID(),
    val name:String,
    val author:String,
    val publicYear:Int,
    val theme:String
)
