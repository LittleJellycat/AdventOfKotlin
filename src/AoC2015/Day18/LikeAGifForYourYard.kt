package AoC2015.Day18

import java.nio.file.Files
import java.nio.file.Paths

fun main(args: Array<String>) {
    val gif = Files.readAllLines(Paths.get(args[0])).map { it.toCharArray().map { it != '.' }.toBooleanArray() }.toTypedArray()
    println(generateSequence(gif, ::getNextFrame).take(101).last().map { it.count { it } }.sum())
    val n = gif.size
    gif[0][0] = true
    gif[0][n - 1] = true
    gif[n - 1][0] = true
    gif[n - 1][n - 1] = true
    println(generateSequence(gif, ::getNextStuckFrame).take(101).last().map { it.count { it } }.sum())
}

private fun getNextFrame(grid: Array<BooleanArray>): Array<BooleanArray> {
    val n = grid.size
    val next = Array(n) { BooleanArray(n) }
    for (i in 0 until n) {
        for (j in 0 until n) {
//             var onNeighbors = 0
//            for (k in i - 1..i + 1) {
//                for (m in j - 1..j + 1) {
//                    if ((k to m != i to j) && k in (0 until n) && m in (0 until n) && grid[k][m])
//                        onNeighbors++
//                }
//            }

            val onNeighbors = (i - 1..i + 1).flatMap { k -> (j - 1..j + 1).map { k to it } }.count { (k, m) ->
                (k to m != i to j) && k in (0 until n) && m in (0 until n) && grid[k][m]
            }

            next[i][j] = if (grid[i][j] && onNeighbors !in (2..3)) {
                false
            } else if (!grid[i][j] && onNeighbors == 3) {
                true
            } else {
                grid[i][j]
            }

        }
    }
    return next
}

private fun getNextStuckFrame(grid: Array<BooleanArray>): Array<BooleanArray> {
    val n = grid.size
    val next = Array(n) { BooleanArray(n) }
    for (i in 0 until n) {
        for (j in 0 until n) {
//             var onNeighbors = 0
//            for (k in i - 1..i + 1) {
//                for (m in j - 1..j + 1) {
//                    if ((k to m != i to j) && k in (0 until n) && m in (0 until n) && grid[k][m])
//                        onNeighbors++
//                }
//            }

            val onNeighbors = (i - 1..i + 1).flatMap { k -> (j - 1..j + 1).map { k to it } }.count { (k, m) ->
                (k to m != i to j) && k in (0 until n) && m in (0 until n) && grid[k][m]
            }

            next[i][j] = if (grid[i][j] && onNeighbors !in (2..3)) {
                false
            } else if (!grid[i][j] && onNeighbors == 3) {
                true
            } else {
                grid[i][j]
            }

        }
    }
    next[0][0] = true
    next[0][n - 1] = true
    next[n - 1][0] = true
    next[n - 1][n - 1] = true
    return next
}