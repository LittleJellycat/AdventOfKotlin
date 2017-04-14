package AoC2015

import java.io.File

private fun sumHouses(trace: String): MutableSet<Pair<Int, Int>>{
    var x = 0
    var y = 0
    val setOfCoords: MutableSet<Pair<Int, Int>> = mutableSetOf(x to y)
    trace.asSequence().forEach {
        when(it){
            '>' -> x++
            '<' -> x--
            '^' -> y++
            'v' -> y--
        }
        setOfCoords.add(x to y)
    }
    return setOfCoords //now returns the set itself
}

private fun sumHousesForBoth(trace: String): Set<Pair<Int, Int>>{
    return (sumHouses(trace.filterIndexed { i, _ -> i % 2 == 0 })
            .union(sumHouses(trace.filterIndexed { i, _ -> i % 2 != 0 })))
}

fun main(args: Array<String>) {
    val trace = File("C:\\Users\\kotat\\input.txt").readText()
    println(sumHouses(trace).size)
    println(sumHousesForBoth(trace).size)
}
