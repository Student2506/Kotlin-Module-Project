package utils

class MenuBuilder {
    companion object {
        fun <T> getMenu(header: String, elements: MutableList<T>): String {
            val repositoryMenu = StringBuilder(header)
            val archivesList = StringBuilder()
            for ((index, element) in elements.withIndex()) {
                archivesList.append("${index + 1}. $element\n")
            }
            val exit = elements.size + 1
            repositoryMenu.append(archivesList)
            repositoryMenu.append("${exit}. Выход\n")
            return repositoryMenu.toString()
        }
    }
}