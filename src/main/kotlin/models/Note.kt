package models

data class Note(val title: String, val content: String) {
    override fun toString(): String = title

    fun getFullNote(): String = "Наименование:\n$title\nСодержимое:\n$content"
}
