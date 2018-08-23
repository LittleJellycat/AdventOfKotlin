import java.io.File

fun main(args: Array<String>) {
    val input = File(args[0]).readLines().map { it.split(" ").map { it.toCharArray().sorted() } }
    val input2 = File(args[0]).readLines().map { it.split(" ") }
    println(input.filter { it.size == it.distinct().size }.count())
    println(input2.filter { it.size == it.distinct().size }.count())
}