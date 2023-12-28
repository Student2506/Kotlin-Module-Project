package utils

interface Fetcher {
    fun getChoice(input: Int): Unit
}

class MenuReader {
    companion object {
        fun <T> router(
            menu: () -> String,
            builder: () -> Unit,
            fetcher: Fetcher,
            archiveToEnlist: MutableList<T>
        ) {
            while (true) {
                println(menu())
                when (val input = readln().toIntOrNull() ?: -1) {
                    0 -> builder()
                    archiveToEnlist.size + 1 -> break
                    else -> {
                        try {
                            fetcher.getChoice(input)
                        } catch (_: IndexOutOfBoundsException) {
                            println("Выберите пункт от 0 до ${archiveToEnlist.size + 1}")
                        }
                    }
                }
            }
        }
    }
}