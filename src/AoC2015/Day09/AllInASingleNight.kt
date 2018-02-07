package AoC2015.Day09

import java.io.File
import java.util.*

fun main(args: Array<String>) {
    val input = File(args[0]).readLines()
            .map { it.split(" ") }
    val listOfDistances = input.map { Triple(it[0], it[2], it[4].toInt()) }
    println(findShortestDistanceLength(listOfDistances))
    println(findLongestDistanceLength(listOfDistances))
}

private fun findShortestDistanceLength(listOfDistances: PairsToInts) =
        list(listOfDistances).min() ?: Int.MAX_VALUE

private fun findLongestDistanceLength(listOfDistances: PairsToInts) =
        list(listOfDistances).max() ?: Int.MIN_VALUE

private fun list(listOfDistances: PairsToInts): List<Int> {
    val locations = listOfDistances.flatMap { listOf(it.first, it.second) }
            .distinct()
    val permutations = getPermutations(locations)
    return permutations.map {
        it.zipWithNext()
                .map { (first, second) ->
                    listOfDistances.find {
                        (it.first == first && it.second == second)
                                || (it.first == second && it.second == first)
                    }!!.third
                }.sum()
    }
}

private fun getPermutations(list: List<String>): MutableList<MutableList<String>> {
    if (list.size == 1) return mutableListOf(list.toMutableList())
    val result = mutableListOf<MutableList<String>>()
    list.forEach { str ->
        val strings = list.toMutableList()
        strings.remove(str)
        val permutations = getPermutations(strings)
        permutations.forEach {
            it.add(0, str)
            result.add(it)
        }
    }
    return result
}

private typealias PairsToInts = List<Triple<String, String, Int>>