package AoC2015.Day01

import java.io.File

private fun findBasement(trace: String): Int {
    var level = 0
    (0 until trace.length).forEach { i ->
        //boring solution
        if (trace[i] == '(') {
            level++
        } else {
            level--
        }
        if (level == -1) {
            return i + 1
        }
    }
    return 0
}

private fun findFloor(trace: String): Int = trace.length - 2 * trace.filter { it == ')' }.length

fun main(args: Array<String>) {
    val trace = File(args[0]).readText()
    println(findFloor(trace))
    println(findBasement(trace))
}