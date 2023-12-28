package utils

interface Fetcher {
    fun getChoice(input: Int): Unit
}

class MenuReader {
    companion object {
        fun <T> router(menu: () -> String, builder: () -> Unit, fetcher: Fetcher, archiveToEnlist: MutableList<T>)  {
            while (true) {
                println(menu())
                when (val input = readln().toIntOrNull() ?: -1) {
                    0 -> builder()
                    archiveToEnlist.size+1 -> break
                    else -> fetcher.getChoice(input)
                }
            }
        }
    }
}