package viewmodel

import models.Archive
import models.Note
import utils.Fetcher
import utils.MenuBuilder
import utils.MenuReader

class ArchiveStorage(archive: Archive) {
    private val archive: Archive

    init {
        this.archive = archive
    }

    private fun getMenu(): String = MenuBuilder.getMenu(
        MENU_HEADER.trimIndent(),
        archive.notes
    )


    fun getChoice() {
        return MenuReader.router(
            menu = ::getMenu,
            builder = ::createNote,
            fetcher = object : Fetcher {
                override fun getChoice(input: Int) {
                    val note = getNote(input)
                    println(note.getFullNote())
                }
            },
            archiveToEnlist = archive.notes
        )
    }

    fun getNote(index: Int): Note {
        return archive.notes[index - 1]
    }

    private fun createNote() {
        var title = ""
        var content = ""
        while (title.isEmpty()) {
            println(TITLE_PROMPT)
            title = readln()
        }
        while (content.isEmpty()) {
            println(CONTENT_PROMPT)
            content = readln()
        }
        archive.notes.add(Note(title = title, content = content))
        println(SUCCESS_CREATION)
    }

    companion object {
        private const val TITLE_PROMPT = "Введите название заметки:"
        private const val SUCCESS_CREATION = "Заметка создана успешно."
        private const val CONTENT_PROMPT = "Введите текст заметки:"
        private const val MENU_HEADER =
            """
            Список заметок:
            0. Создать заметку
        
            """
    }
}