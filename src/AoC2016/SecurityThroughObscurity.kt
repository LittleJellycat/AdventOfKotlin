package AoC2016

import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

fun main(args: Array<String>) {
    val rooms = Files.readAllLines(Paths.get("C:\\Users\\MMUSER\\IdeaProjects\\AdventOfKotlin\\src\\AoC2016\\rooms")).map { it.trimEnd(']').split("-", "[") }.map { listOf(it.subList(0, it.size - 2).joinToString(""), it[it.size - 2], it[it.size - 1]) }
    println(rooms.filter { it[2] == topLetters(it[0]) }.sumBy { it[1].toInt() })
    println(rooms.filter { rotate(it[0], it[1].toInt()).contains("northpole") })
}

private fun topLetters(s: String): String {
    val word = s.toCharArray().sorted()
//    val letters = ('a'..'z').toList()
//            .zip((generateSequence(0, { x -> 0 }).take(26)).toList()).toMap()
    val letters = HashMap<Char, Int>()
    ('a'..'z').map { letters.put(it, 0) }
    word.forEach { ch ->
        letters.computeIfPresent(ch, { _, value -> value + 1 })
    }
    return letters.entries.sortedByDescending { it.component2() }.take(5).map { it.component1() }.joinToString("")
}

private fun rotate(s: String, n: Int): String {
    val letters = ('a'..'z').toList()
    val times = n % 26
    val chars = s.toCharArray().map {
        letters[(letters.lastIndexOf(it) + times) % 26]
    }
    return chars.joinToString("")
}