package AoC2017.Day06

import java.io.File

fun main(args: Array<String>) {
    val input = File(args[0]).readText().split("\\s+".toRegex()).map(String::toInt)
    println(findFirstRepeatedNumber(input))
}

private fun findFirstRepeatedNumber(buckets: List<Int>): Int {
    val combinations = mutableSetOf<List<Int>>()
    println(generateSequence(buckets) { reallocate(it) }.indexOfFirst { !combinations.add(it) })
    return combinations.size
}


private fun reallocate(buckets: List<Int>): List<Int> {
    print(buckets)
    val n = buckets.indexOf(buckets.max())
    val multiplier = buckets[n] / buckets.size
    val tailLength = buckets[n] % buckets.size
    return buckets.mapIndexed { i, elem ->
        when {
            i == n -> multiplier + (if (i < tailLength) 1 else 0)
            // (posMod(i - n, buckets.size) <= tailLength) -> elem + multiplier + 1
            else -> elem + multiplier
        }
    }
}

private fun posMod(i: Int, n: Int): Int {
    val r = i % n
    return if (r > 0) r else r + n
}

