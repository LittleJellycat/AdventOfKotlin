package AoC2015

import java.io.File
import java.util.*

fun main(args: Array<String>) {
    val rawList = File("C:\\Users\\kotat\\Documents\\input13.txt")
            .readLines().map { it.split(" ") }
    println(findOptimalSeating(getRelationships(rawList)))
}

private fun findOptimalSeating(relationships: List<Triple<String, String, Int>>): Int {
    val people = relationships.flatMap { listOf(it.first, it.second) }.distinct() as ArrayList
    val permutations = getPermutations(people)
    val satisfaction = permutations.map { permutation ->
        val pSize = permutation.size
        (0..pSize - 1)
                .map { permutation[it % pSize] to permutation[(it + 1) % pSize] }
                .map { pair ->
                    relationships.find {
                        (it.first == pair.first && it.second == pair.second)
                    }!!.third + relationships.find {
                        (it.first == pair.second && it.second == pair.first)
                    }!!.third
                }.sum()
    }
    return satisfaction.max() ?: Int.MIN_VALUE
}

//private fun getOptimalSeatingWithNeutral(relationships: List<Triple<String, String, Int>>): Int {
//    val people = relationships.flatMap { listOf(it.first, it.second) }.distinct() as ArrayList
//    val permutations = getPermutations(people)
//    var worstValue = Int.MIN_VALUE
//    var permutationsValues = ArrayList<Pair<ArrayList<String>, Int>>()
//    val satisfaction = permutations.map { permutation ->
//        val pSize = permutation.size
//        permutationsValues.add(permutation to
//        (0..pSize - 1)
//                .map { permutation[it % pSize] to permutation[(it + 1) % pSize] }
//                .map {  pair ->
//                    relationships.find {
//                        (it.first == pair.first && it.second == pair.second)
//                    }!!.third + relationships.find {
//                        (it.first == pair.second && it.second == pair.first)
//                    }!!.third
//                }.sum())
//    }
//    return findOptimalSeating(relationships) - permutationsValues.find {
//
//    }
//}

private fun getRelationships(rawList: List<List<String>>): List<Triple<String, String, Int>> {
    return rawList.map {
        Triple(it[0], it[10].trimEnd('.'),
                if (it[2] == "lose") -1 * it[3].toInt() else it[3].toInt())
    }
}

private fun getPermutations(list: ArrayList<String>): ArrayList<ArrayList<String>> {
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

