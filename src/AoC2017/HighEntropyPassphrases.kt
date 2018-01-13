import java.nio.file.Files
import java.nio.file.Paths

fun main(args: Array<String>) {
    val input = Files.readAllLines(Paths.get("C:\\Users\\MMUSER\\IdeaProjects\\untitled1\\src\\passphrases.txt")).map { it.split(" ").map { it.toCharArray().sorted() } }
    val input2 = Files.readAllLines(Paths.get("C:\\Users\\MMUSER\\IdeaProjects\\untitled1\\src\\passphrases.txt")).map { it.split(" ")}
    println(input.filter { it.size == it.distinct().size }.count())
    println(input2.filter { it.size == it.distinct().size }.count())
}