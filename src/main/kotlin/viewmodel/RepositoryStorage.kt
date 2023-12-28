package viewmodel

import models.Archive
import models.Repository
import utils.Fetcher
import utils.MenuBuilder
import utils.MenuReader

class RepositoryStorage {
    private val repository = Repository(mutableListOf())
    private fun getMenu(): String = MenuBuilder.getMenu(
        MENU_HEADER.trimIndent(),
        repository.archives
    )

    fun getArchive(index: Int): Archive {
        return repository.archives[index - 1]
    }


    private fun createArchive() {
        var title = ""
        while (title == "") {
            println(TITLE_PROMPT)
            title = readln()
        }
        repository.archives.add(Archive(title = title, notes = mutableListOf()))
        println(SUCCESS_CREATION)
    }

    fun getChoice() {
        return MenuReader.router(
            menu = ::getMenu,
            builder = ::createArchive,
            fetcher = object : Fetcher {
                override fun getChoice(input: Int) {
                    val archive = ArchiveStorage(getArchive(input))
                    archive.getChoice()
                }
            },
            archiveToEnlist = repository.archives
        )
    }

    companion object {
        private const val TITLE_PROMPT = "Введите название архива:"
        private const val SUCCESS_CREATION = "Архив создан успешно."
        private const val MENU_HEADER =
            """
            Список архивов:
            0. Создать архив
        
            """
    }
}