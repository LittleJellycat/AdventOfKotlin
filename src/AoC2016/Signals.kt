package AoC2016

import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

fun main(args: Array<String>) {
    val input = Files.readAllLines(Paths.get("C:\\Users\\MMUSER\\IdeaProjects\\AdventOfKotlin\\src\\AoC2016\\input"))
    val rotated = ArrayList<String>()
    for (i in 0 until input.first().length) {
        val word = StringBuilder()
        for (j in 0 until input.size) {
            word.append(input[j][i])
        }
        rotated.add(word.toString())
    }
    println(rotated.map { topLetter(it) }.joinToString(""))
    println(rotated.map { leastCommonLetter(it) }.joinToString(""))
}

private fun topLetter(s: String): Char {
    val word = s.toCharArray().sorted()
//    val letters = ('a'..'z').toList()
//            .zip((generateSequence(0, { x -> 0 }).take(26)).toList()).toMap()
    val letters = HashMap<Char, Int>()
    ('a'..'z').map { letters.put(it, 0) }
    word.forEach { ch ->
        letters.computeIfPresent(ch, { _, value -> value + 1 })
    }
    return letters.entries.sortedByDescending { it.component2() }.first().component1()
}

private fun leastCommonLetter(s: String): Char {
    val word = s.toCharArray().sorted()
//    val letters = ('a'..'z').toList()
//            .zip((generateSequence(0, { x -> 0 }).take(26)).toList()).toMap()
    val letters = HashMap<Char, Int>()
    ('a'..'z').map { letters.put(it, 0) }
    word.forEach { ch ->
        letters.computeIfPresent(ch, { _, value -> value + 1 })
    }
    return letters.entries.sortedBy{ it.component2() }.first().component1()
}