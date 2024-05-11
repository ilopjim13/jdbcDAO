package utilities

import output.Console
import java.util.*

class Utilities {

    companion object {

        private val uuids = mutableListOf<UUID>()

        fun typeChoose(console: Console):Boolean {
            console.showMessage("1. File")
            console.showMessage("2. Database")
            console.showMessage(">> Choose an option: ", false)
            var option = -1
            do {
                try {
                    option = readln().toInt()
                    if (option !in (1..2)) {
                        console.showMessage("**ERROR** Must be a valid option.")
                        console.showMessage(">> Choose an option: ", false)
                    }
                } catch (e: NumberFormatException) {
                    console.showMessage("**ERROR** Must be a valid option.")
                    console.showMessage(">> Choose an option: ", false)
                }
            } while(option !in (1..2))

            return when (option) {
                1 -> false
                2 -> true
                else -> true
            }
        }

        fun generateUUID(): UUID {
            var id:UUID
            do {
                id = UUID.randomUUID()
            } while (id in uuids)
            uuids.add(id)
            return id
        }

    }

}