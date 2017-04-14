package AoC2016

import java.io.File
import java.util.regex.Pattern

fun main(args: Array<String>) {
    println(File("C:\\Users\\kotat\\Documents\\input3.txt").readLines()
            .map { it.trim().split("\\s+").map(String::toInt).sorted() }
            .count { it[0] + it [1] > it [2] })
}