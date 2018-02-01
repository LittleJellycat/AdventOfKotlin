package AoC2016

import java.nio.file.Files
import java.nio.file.Paths

fun main(args: Array<String>) {
    val instructions = Files.readAllLines(Paths.get("C:\\Users\\MMUSER\\IdeaProjects\\AdventOfKotlin\\src\\AoC2016\\instr"))
    println(execute(instructions))
}

private fun execute(instructions: List<String>): Long? {
    val registry = mutableMapOf("a" to 0L, "b" to 0L, "c" to 0L, "d" to 0L)
    val instr = instructions.map { it.split(" ").map { it.replace(",", "") } }
    val r = { x: Int, y: Int -> instr[x][y] }
    var i = 0

//    cpy x y copies x (either an integer or the value of a register) into register y.
//    inc x increases the value of register x by one.
//    dec x decreases the value of register x by one.
//    jnz x y jumps to an instruction y away (positive means forward; negative means backward), but only if x is not zero.

    while (i < instructions.size) {
        val instruction = instr[i][0]
        when (instruction) {
            "cpy" -> {
                if (!r(i, 1)[0].isDigit()) registry.put(r(i, 2), registry[r(i, 1)]!!) else registry.put(r(i, 2), r(i, 1).toLong())
                i++
            }
            "inc" -> {
                registry.computeIfPresent(r(i, 1)) { _, value -> value + 1 }
                i++
            }
            "dec" -> {
                registry.computeIfPresent(r(i, 1)) { _, value -> value - 1 }
                i++
            }
            "jmz" -> if (r(i, 1).toInt() != 0) i += r(i, 2).toInt()
        }

    }
    return registry["a"]
}