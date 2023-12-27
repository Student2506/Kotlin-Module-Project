import viewmodel.ArchiveStorage
import viewmodel.RepositoryStorage

fun main(args: Array<String>) {
    val repositoryStorage = RepositoryStorage()

    do {
        println(repositoryStorage)
        val input = readln().toIntOrNull() ?: -1
        when (input) {
            0 -> repositoryStorage.createArchive()
            repositoryStorage.exitOption -> continue
            else -> {
                val archive = ArchiveStorage(repositoryStorage.getArchive(input))
                archive.getChoice()
            }
        }
    } while (!repositoryStorage.isExit(input))
}