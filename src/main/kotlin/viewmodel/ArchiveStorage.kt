package viewmodel

import models.Archive
import models.Note

class ArchiveStorage(archive: Archive) {
    private val archive: Archive

    init {
        this.archive = archive
    }

    fun getMenu(): String {
        val archiveMenu = StringBuilder(
            """
            Список заметок:
            0. Создать заметку
        
        """.trimIndent()
        ) // Intentionally left hanging end-of-line
        val notesList = StringBuilder()
        for ((index, archive) in archive.notes.withIndex()) {
            notesList.append("${index + 1}. $archive\n")
        }
        val exit = archive.notes.size + 1
        archiveMenu.append(notesList)
        archiveMenu.append("${exit}. Выход\n")
        return archiveMenu.toString()
    }

    fun getChoice() {
        while (true) {
            println(getMenu())
            val input = readln().toIntOrNull() ?: -1
            when (input) {
                0 -> createNote()
                exitOption -> break
                else -> {
                    val note = getNote(input)
                    println(note)
                }
            }
        }
    }

    fun getNote(index: Int): String {
        return archive.notes[index-1].getFullNote()
    }

    val exitOption: Int
        get() = archive.notes.size + 1

    fun createNote() {
        println(TITLE_PROMPT)
        val title = readln()
        println(CONTENT_PROMPT)
        val content = readln()
        archive.notes.add(Note(title = title, content = content))
        println(SUCCESS_CREATION)
    }

    companion object {
        private const val TITLE_PROMPT = "Введите название заметки:"
        private const val SUCCESS_CREATION = "Заметка создана успешно."
        private const val CONTENT_PROMPT = "Введите текст заметки:"
    }
}