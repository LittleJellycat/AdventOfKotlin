package AoC2015

import java.io.File
import java.util.*

fun main(args: Array<String>) {
    val input = File("C:\\Users\\kotat\\Documents\\input9.txt").readLines()
            .map { it.split(" ") }
    val listOfDistances = input.map { Triple(it[0], it[2], it[4].toInt()) }
    println(findShortestDistanceLength(listOfDistances))
    println(findLongestDistanceLength(listOfDistances))
}

fun findShortestDistanceLength(listOfDistances: List<Triple<String, String, Int>>) =
        list(listOfDistances).min() ?: Int.MAX_VALUE

fun findLongestDistanceLength(listOfDistances: List<Triple<String, String, Int>>) =
        list(listOfDistances).max() ?: Int.MIN_VALUE

private fun list(listOfDistances: List<Triple<String, String, Int>>): List<Int> {
    val locations = listOfDistances.flatMap { listOf(it.first, it.second) }.distinct() as ArrayList
    val permutations = getPermutations(locations)
    val distances = permutations.map { permutation ->
        (0..permutation.size - 2)
                .map { permutation[it] to permutation[it + 1] }
                .map { pair ->
                    listOfDistances.find {
                        (it.first == pair.first && it.second == pair.second)
                                || (it.first == pair.second && it.second == pair.first)
                    }!!.third
                }.sum()
    }
    return distances
}


fun getPermutations(list: ArrayList<String>): ArrayList<ArrayList<String>> {
    if (list.size == 1) return ArrayList(arrayListOf(list))
    val result = ArrayList<ArrayList<String>>()
    list.forEach { str ->
        val strings = ArrayList<String>(list)
        strings.remove(str)
        val permutations = getPermutations(strings)
        permutations.forEach {
            it.add(0, str)
            result.add(it)
        }
    }
    return result
}