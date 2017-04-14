package AoC2016

import java.io.File
import java.util.*

fun main(args: Array<String>) {
    val input = File("C:\\Users\\kotat\\Documents\\input.txt").readText()
    val trace = input.split(", ")
    println(findGridPathLength(trace))
}

private fun findGridPathLength(trace: List<String>): Int {
    val coords: MutablePair = MutablePair(0, 0)
    var direction = 0
    val listOfCoords = ArrayList<Pair<Int, Int>>()
    trace.forEach {
        when (it.first()) {
            'L' -> direction = (direction - 1 + 4) % 4
            'R' -> direction = (direction + 1 + 4) % 4
        }
        val length = it.drop(1).toInt()
        when (direction) {
            0 -> coords.first += length
            1 -> coords.second += length
            2 -> coords.first -= length
            3 -> coords.second -= length
        }

        if (listOfCoords.contains(coords.toPair())) {
            return Math.abs(coords.first) + Math.abs(coords.second)
        } else {
            listOfCoords.add(coords.toPair())
        }
    }
    return Math.abs(coords.first) + Math.abs(coords.second)
}

private data class MutablePair(var first: Int, var second: Int) {
    fun toPair(): Pair<Int, Int> = Pair(first, second)
}
