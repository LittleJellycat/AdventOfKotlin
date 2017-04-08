package AoC2015

import java.io.File

fun main(args: Array<String>) {
    val lines = File("C:\\Users\\kotat\\Documents\\input8.txt")
            .readLines()
    val fileSize = lines.sumBy { it.length }
    println(countChars(filterChars(lines), fileSize))
}

fun countChars(lines: List<String>, size: Int): Int {
    var diff = size
    lines.forEach { diff -= it.length }
    return diff
}

fun filterChars(lines: List<String>): List<String> {
    val clearedLines = lines.map {
        it.trimStart('\"').trimEnd('\"')
                .replace(Regex("\\\\x[0-9a-f]{2}"), "a")
                .replace(Regex("\\\\\\\\"), "a")
                .replace(Regex("\\\\\""), "a")
    }
    return clearedLines
}
