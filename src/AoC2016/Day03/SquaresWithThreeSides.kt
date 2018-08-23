package AoC2016.Day03

import java.io.File

fun main(args: Array<String>) {
    val input = File(args[0]).readLines()
    println(countFromVerticalList(input))
//    println(input
//            .map { it.trim().split("\\s+").map(String::toInt).sorted() }
//            .count { it[0] + it[1] > it[2] })
}

private fun countFromVerticalList(input: List<String>): Int {
    return input
            .map { it.trim().split("\\s+".toRegex()).map(String::toInt) }
            .flatten()
            .chunked(9) {
                listOf(isTriangle(it[0], it[3], it[6]),
                        isTriangle(it[1], it[4], it[7]),
                        isTriangle(it[2], it[5], it[8]))
            }.flatten().count()
}


private fun isTriangle(a: Int, b: Int, c: Int): Boolean {
    val sides = listOf(a, b, c).sorted()
    return sides[0] + sides[1] > sides[2]
}