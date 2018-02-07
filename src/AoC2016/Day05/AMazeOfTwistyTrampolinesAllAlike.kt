package AoC2016.Day05

import java.io.File

fun main(args: Array<String>) {
    val instructions = File(args[0]).readLines().map { it.toInt() }.toMutableList()
    val limit = instructions.size - 1
    var current = 0
    var n=1
    while (current < limit) {
        val next = instructions[current] + current
        instructions[current]++
        current=next
        n++
    }
    println(n)
}