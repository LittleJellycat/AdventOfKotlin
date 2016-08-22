import java.io.File

/**
 * Created by Jelly on 22.08.2016.
 */

fun main(args: Array<String>) {
    val dimensionsList = File("C:\\Users\\kotat\\input.txt").readLines()
    println(getPaperSize(dimensionsList))
    println(getRibbonSize(dimensionsList))
}

fun getPaperSize(dimensions: List<String>): Int {
    return dimensions.asSequence().map { splitToTriplets(it) }
            .map { 2 * (it.first * it.second + it.first * it.third + it.second * it.third) + it.first * it.second }.sum()
}

fun getRibbonSize(dimensions: List<String>): Int {
    return dimensions.asSequence().map { splitToTriplets(it) }
            .map { 2 * (it.first + it.second) + it.first * it.second * it.third }.sum()
}

fun splitToTriplets(elem: String): Triple<Int, Int, Int> = elem.split("x").map { it.toInt() }.sorted()
        .let { Triple(it[0], it[1], it[2]) }