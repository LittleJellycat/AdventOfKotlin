package AoC2015

import java.io.File

fun findBasement(trace: String): Int {
    var level = 0
    for (i in (0..trace.length - 1)) { //boring solution
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

fun findFloor(trace: String): Int {
    return trace.length - 2 * trace.filter { it == ')' }.length
}

fun main(args: Array<String>) {
    val trace = File("C:\\Users\\kotat\\input.txt").readText()
    println(findFloor(trace))
    println(findBasement(trace))
}