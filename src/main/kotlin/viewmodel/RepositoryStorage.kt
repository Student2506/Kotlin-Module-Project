package viewmodel

import models.Archive
import models.Repository

class RepositoryStorage {
    private val repository = Repository(mutableListOf())
    override fun toString(): String {
        val repositoryMenu = StringBuilder(
            """
            Список архивов:
            0. Создать архив
        
        """.trimIndent()
        ) // Intentionally left hanging end-of-line
        val archivesList = StringBuilder()
        for ((index, archive) in repository.archives.withIndex()) {
            archivesList.append("${index + 1}. $archive\n")
        }
        val exit = repository.archives.size + 1
        repositoryMenu.append(archivesList)
        repositoryMenu.append("${exit}. Выход\n")
        return repositoryMenu.toString()
    }

    fun getArchive(index: Int): Archive {
        return repository.archives[index-1]
    }

    fun isExit(option: Int): Boolean = option == repository.archives.size + 1

    val exitOption: Int
        get() = repository.archives.size + 1

    fun createArchive() {
        println(TITLE_PROMPT)
        val title = readln()
        repository.archives.add(Archive(title = title, notes = mutableListOf()))
        println(SUCCESS_CREATION)
    }

    companion object {
        private const val TITLE_PROMPT = "Введите название архива:"
        private const val SUCCESS_CREATION = "Архив создан успешно."
    }
}