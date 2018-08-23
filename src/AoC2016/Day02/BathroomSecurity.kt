package AoC2016.Day02

import java.io.File

fun main(args: Array<String>) {
    val input = File(args[0]).readLines()
    println(getCode(input))
    println(getSupercode(input))
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


private fun getSupercode(input: List<String>): String {
    val numpad = arrayOf(
            arrayOf("x", "x", "1", "x", "x"),
            arrayOf("x", "2", "3", "4", "x"),
            arrayOf("5", "6", "7", "8", "9"),
            arrayOf("x", "A", "B", "C", "x"),
            arrayOf("x", "x", "D", "x", "x")
    )
    var x = 2
    var y = 0
    val code = StringBuilder()
    input.forEach {
        it.forEach {
            when (it) {
                'L' -> if (y in 1..4 && numpad[x][y - 1] != "x") y--
                'R' -> if (y in 0..3 && numpad[x][y + 1] != "x") y++
                'U' -> if (x in 1..4 && numpad[x - 1][y] != "x") x--
                'D' -> if (x in 0..3 && numpad[x + 1][y] != "x") x++
            }
        }
        code.append(numpad[x][y])
    }
    return code.toString()
}