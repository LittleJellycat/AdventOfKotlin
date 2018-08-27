package AoC2017.Day12

import java.io.File

fun main(args: Array<String>) {
    val input = File(args[0])
            .readLines()
            .map {
                it.replace("\\s".toRegex(), "")
                        .split("<->", ",")
                        .map(String::toInt)
            }
//    println(getReachable(buildAdjacencyMatrix(input), 0).count())
    println(getNumberOfGroups(buildAdjacencyMatrix(input)))
}

private fun buildAdjacencyMatrix(pipes: List<List<Int>>): Array<BooleanArray> {
    val m = Array(pipes.size) { BooleanArray(pipes.size) }
    for (pipe in pipes) {
        val head = pipe.first()
        val tail = pipe.drop(1)
        for (p in tail) {
            m[head][p] = true
            m[p][head] = true
        }
    }
    return m
}

private fun getReachable(m: Array<BooleanArray>, n: Int): Set<Int> {
    val reachable = mutableSetOf(0)
    val queue = mutableListOf<Int>()
    queue.addAll(m[n].mapIndexed { index, b -> if (b) index else -1 }.filter { it != -1 })
    while (queue.isNotEmpty()) {
        val current = queue.removeAt(0)
        if (!reachable.contains(current)) {
            reachable.add(current)
            queue.addAll(m[current]
                    .mapIndexed { index, b -> if (b) index else -1 }
                    .filter { it != -1 && !reachable.contains(it) })
        }
    }
    return reachable
}

private fun getNumberOfGroups(m: Array<BooleanArray>): Int {
    var count = 0
    var vertices = (0 until m.size).toList()
    while (vertices.isNotEmpty()) {
        val group = getReachable(m, vertices.first())
        vertices = vertices.filter { !group.contains(it) }
        count++
    }
    return count
}