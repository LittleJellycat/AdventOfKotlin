package AoC2016.Day01

import java.io.File
import java.util.*

fun main(args: Array<String>) {
    val input = File(args[0]).readText()
    val trace = input.split(", ")
    println(findGridPathLength(trace))
    println(findPathToVisitedTwice(trace))
}

private fun findGridPathLength(trace: List<String>): Int {
    val coords = MutablePair(0, 0)
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

private fun findPathToVisitedTwice(trace: List<String>): Int {
    val visitedCoordinates = mutableSetOf(0 to 0)
    var x = 0
    var y = 0
    var direction = 0
    trace.forEach {
        val prevX = x
        val prevY = y
        when (it.first()) {
            'L' -> direction = (direction - 1 + 4) % 4
            'R' -> direction = (direction + 1 + 4) % 4
        }
        val length = it.drop(1).toInt()
        when (direction) {
            0 -> x += length
            1 -> y += length
            2 -> x -= length
            3 -> y -= length
        }
        for (i in (Math.min(x, prevX)..Math.max(x, prevX))) {
            for (j in (Math.min(y, prevY)..Math.max(y, prevY))) {
                if ((i to j != prevX to prevY) && !visitedCoordinates.add(i to j)) {
                    return Math.abs(i) + Math.abs(j)
                }
            }
        }

    }
    return -1
}

