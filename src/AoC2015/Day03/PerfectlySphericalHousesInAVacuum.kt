package AoC2015.Day03

import java.io.File

private fun sumHouses(trace: String): MutableSet<Pair<Int, Int>>{
    var x = 0
    var y = 0
    val setOfCoordinates: MutableSet<Pair<Int, Int>> = mutableSetOf(x to y)
    trace.asSequence().forEach {
        when(it){
            '>' -> x++
            '<' -> x--
            '^' -> y++
            'v' -> y--
        }
        setOfCoordinates.add(x to y)
    }
    return setOfCoordinates //now returns the set itself
}

private fun sumHousesForBoth(trace: String): Set<Pair<Int, Int>>{
    return (sumHouses(trace.filterIndexed { i, _ -> i % 2 == 0 })
            .union(sumHouses(trace.filterIndexed { i, _ -> i % 2 != 0 })))
}

fun main(args: Array<String>) {
    val trace = File(args[0]).readText()
    println(sumHouses(trace).size)
    println(sumHousesForBoth(trace).size)
}
