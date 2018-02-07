package AoC2015.Day05

import java.io.File


fun main(args: Array<String>) {
    val testStrings = File(args[0]).readLines()
    println(testStrings.filter(::isNice).count())
    println(testStrings.filter(::isEvenNicer).count())
}

private fun isNice(str: String): Boolean =
        str.zipWithNext().none {
            when (it) {
                'a' to 'b', 'c' to 'd', 'p' to 'q', 'x' to 'y' -> true
                else -> false
            }
        }
                && str.zipWithNext().any { it.first == it.second }
                && str.count { it in listOf('a', 'e', 'i', 'o', 'u') } >= 3

private fun isEvenNicer(str: String): Boolean {
    val pair = "(\\w{2}).*\\1".toRegex()
    return str.contains(pair) && str.windowed(3).any { it[0] == it[2] }
}
