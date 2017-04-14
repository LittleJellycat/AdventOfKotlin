package AoC2016

import java.io.File

fun main(args: Array<String>) {
    val input = File("C:\\Users\\kotat\\Documents\\input2.txt").readLines()
    println(getCode(input))
}

private fun getCode(input: List<String>): List<Int> {
    var x = 2
    var y = 2 //as for "5" on the num pad
    if (!listOf(Pair(1, 1), Pair(2, 2)).contains(Pair(x, y))) x++
    val outputList = mutableListOf<Int>()
    input.forEach {
        it.forEach {
            when (it) {
                'L' -> if (x != 1) x--
                'R' -> if (x != 3) x++
                'U' -> if (y != 1) y--
                'D' -> if (y != 3) y++
            }
        }
        outputList.add(3 * (y - 1) + x)
    }
    return outputList
}
