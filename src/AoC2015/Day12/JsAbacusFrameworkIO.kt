package AoC2015.Day12

import java.io.File

fun main(args: Array<String>) {
    val script = File(args[0])
            .readText()
    //println(findSum(script))
    println(removeRed(script))
    println(findSum(removeRed(script)))
}

private fun findSum(script: String): Int = Regex("-?\\d+").findAll(script).sumBy { it.value.toInt() }

private fun removeRed(script: String): String = script.replace(Regex("\\{[^{]+?(:\"red\")*}"), "")
