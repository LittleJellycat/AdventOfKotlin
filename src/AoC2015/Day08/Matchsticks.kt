package AoC2015.Day08

import java.io.File

fun main(args: Array<String>) {
    val lines = File(args[0])
            .readLines()
    val fileSize = lines.sumBy { it.length }
    println(countChars(filterChars(lines), fileSize))
    println(encodeDiff(lines))
}

private fun countChars(lines: List<String>, size: Int): Int = size - lines.sumBy { it.length }

private fun filterChars(lines: List<String>): List<String> = lines.map {
    it.trimStart('\"').trimEnd('\"')
            .replace(Regex("\\\\x[0-9a-f]{2}"), "a")
            .replace(Regex("\\\\\\\\"), "a")
            .replace(Regex("\\\\\""), "a")
}

private fun encodeDiff(lines: List<String>): Int = lines.sumBy {
    2 + it.count { (it == '\\') || (it == '"') }
}