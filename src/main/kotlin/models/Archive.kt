package models

data class Archive(val title: String, val notes: MutableList<Note>) {
    override fun toString(): String {
        return "Архив: $title"
    }
}
