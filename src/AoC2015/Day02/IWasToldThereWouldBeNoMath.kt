package AoC2015.Day02

import java.io.File

fun main(args: Array<String>) {
    val dimensionsList = File(args[0]).readLines()
    println(getPaperSize(dimensionsList))
    println(getRibbonSize(dimensionsList))
}

private fun getPaperSize(dimensions: List<String>): Int {
    return dimensions.asSequence().map(::splitToTriplets)
            .map { 2 * (it.first * it.second + it.first * it.third + it.second * it.third) + it.first * it.second }.sum()
}

private fun getRibbonSize(dimensions: List<String>): Int {
    return dimensions.asSequence().map(::splitToTriplets)
            .map { 2 * (it.first + it.second) + it.first * it.second * it.third }.sum()
}

private fun splitToTriplets(elem: String): Triple<Int, Int, Int> = elem.split("x")
        .map(String::toInt).sorted().let { Triple(it[0], it[1], it[2]) }