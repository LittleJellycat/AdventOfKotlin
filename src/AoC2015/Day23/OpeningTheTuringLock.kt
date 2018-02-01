package AoC2015.Day23

import java.nio.file.Files
import java.nio.file.Paths

fun main(args: Array<String>) {
    val instructions = Files.readAllLines(Paths.get(args[0]))
    println(execute(instructions))
    println(executeNew(instructions))
}

private fun execute(instructions: List<String>): Pair<Int?, Int?> {
    val registry = mutableMapOf("a" to 0, "b" to 0)
    val instr = instructions.map { it.split(" ")
            .map { it.replace(",", "") } }
    val r = { x: Int -> instr[x][1] }
    var i = 0
    while (i < instructions.size) {
        val instruction = instr[i][0]
        when (instruction) {
            "hlf" -> {
                registry.computeIfPresent(r(i)) { _, value -> hlf(value) }
                i++
            }
            "tpl" -> {
                registry.computeIfPresent(r(i)) { _, value -> tpl(value) }
                i++
            }
            "inc" -> {
                registry.computeIfPresent(r(i)) { _, value -> inc(value) }
                i++
            }
            "jmp" -> i += instr[i][1].toInt()
            "jie" -> if (registry[r(i)]!! % 2 == 0) i += instr[i][2].toInt() else i++
            "jio" -> if (registry[r(i)]!! == 1) i += instr[i][2].toInt() else i++
        }

    }
    return registry["a"] to registry["b"]
}


private fun executeNew(instructions: List<String>): Pair<Long?, Long?> {
    val registry = mutableMapOf("a" to 1L, "b" to 0L)
    val instr = instructions.map { it.split(" ")
            .map { it.replace(",", "") } }
    val r = { x: Int -> instr[x][1] }
    var i = 0
    while (i < instructions.size) {
        val instruction = instr[i][0]
        when (instruction) {
            "hlf" -> {
                registry.computeIfPresent(r(i)) { _, value -> value / 2 }
                i++
            }
            "tpl" -> {
                registry.computeIfPresent(r(i)) { _, value -> value * 3 }
                i++
            }
            "inc" -> {
                registry.computeIfPresent(r(i)) { _, value -> value + 1 }
                i++
            }
            "jmp" -> i += instr[i][1].toInt()
            "jie" -> if (registry[r(i)]!! % 2 == 0L) i += instr[i][2].toInt() else i++
            "jio" -> if (registry[r(i)]!! == 1L) i += instr[i][2].toInt() else i++
        }

    }
    return registry["a"] to registry["b"]
}

private fun hlf(x: Int): Int = x / 2
private fun tpl(x: Int): Int = x * 3
private fun inc(x: Int): Int = x + 1