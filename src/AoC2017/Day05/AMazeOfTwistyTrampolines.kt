package AoC2017.Day05

import java.io.File

fun main(args: Array<String>) {
    val input = File(args[0]).readLines().map(String::toInt)
    println(findNumberOfIterations(input))
    println(findNumberOfIterationsRestricted(input))
}

private fun findNumberOfIterations(commands: List<Int>): Int {
    val input = commands.toMutableList()
    var index = 0
    var steps = 0
    do {
        val nextIndex = index + input[index]
        input[index]++
        index = nextIndex
        steps++
    } while (index < input.size)
    return steps
}

private fun findNumberOfIterationsRestricted(commands: List<Int>): Int {
    val input = commands.toMutableList()
    var index = 0
    var steps = 0
    do {
        val nextIndex = index + input[index]
        if (input[index] >= 3) {
            input[index]--
        } else {
            input[index]++
        }
        index = nextIndex
        steps++
    } while (index < input.size)
    return steps
}