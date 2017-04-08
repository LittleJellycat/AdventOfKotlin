package AoC2015

import java.io.File

fun main(args: Array<String>) {
    val lines = File("C:\\Users\\kotat\\Documents\\input8.txt")
            .readLines()
    val fileSize = lines.sumBy { it.length }
    println(countChars(filterChars(lines), fileSize))
    println(encode(lines))
}

fun countChars(lines: List<String>, size: Int): Int {
    return size - lines.sumBy { it.length }
}

fun filterChars(lines: List<String>): List<String> {
    return lines.map {
        it.trimStart('\"').trimEnd('\"')
                .replace(Regex("\\\\x[0-9a-f]{2}"), "a")
                .replace(Regex("\\\\\\\\"), "a")
                .replace(Regex("\\\\\""), "a")
    }
}

fun encode(lines: List<String>): Int {
    return lines.sumBy {
        2 + it.count { (it == '\\') || (it == '"') }
    }
}