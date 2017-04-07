package AoC2015

import java.io.File

fun main(args: Array<String>) {
    val script = File("C:\\Users\\kotat\\Documents\\input12.txt")
            .readText()
    println(findSum(script))
}

fun findSum(script: String): Int {
    return script.split(":", ",").asSequence()
            .filter { it.matches("^-?\\d+".toRegex()) }
            .map(String::toInt).sum()
}
