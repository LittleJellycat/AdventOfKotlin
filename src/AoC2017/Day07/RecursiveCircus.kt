package AoC2017.Day07

import java.io.File

fun main(args: Array<String>) {
    val input = File(args[0]).readLines()
    println(findRoot(input))
}

private fun findRoot(input: List<String>): String {
    val nodes = mutableSetOf<String>()
    val nodesToRemove = mutableSetOf<String>()
    input.forEach {
        if (it.contains("->")) {
            val node = it.split(" \\(\\d+\\) -> ".toRegex())
            nodes.add(node[0])
            nodesToRemove.addAll(node[1].split(", "))
        } else {
            nodes.add(it.split("\\s".toRegex())[0])
        }
    }
    return (nodes - nodesToRemove).first()
}