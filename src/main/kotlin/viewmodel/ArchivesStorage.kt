package viewmodel

import models.Archive
import models.Archives
import utils.Fetcher
import utils.MenuBuilder
import utils.MenuReader

class ArchivesStorage {
    private val archives = Archives(mutableListOf())
    private fun getMenu(): String = MenuBuilder.getMenu(
        MENU_HEADER.trimIndent(),
        archives.archives
    )

    fun getArchive(index: Int): Archive {
        return archives.archives[index - 1]
    }


    private fun createArchive() {
        var title = ""
        while (title.isEmpty()) {
            println(TITLE_PROMPT)
            title = readln()
        }
        archives.archives.add(Archive(title = title, notes = mutableListOf()))
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
            archiveToEnlist = archives.archives
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