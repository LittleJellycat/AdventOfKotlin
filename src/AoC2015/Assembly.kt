package AoC2015

import java.io.File
import java.util.*
import java.util.Arrays.asList

fun main(args: Array<String>) {
    val rawInstructions = File("C:\\Users\\kotat\\Documents\\input7.txt")
            .readLines()
    println(findWireSignal(parseWires(rawInstructions), "a"))
    val newInstructions = rawInstructions.map { if (it.matches(Regex("[0-9a-z]+ -> b"))) "16076 -> b" else it }
    println(findWireSignal(parseWires(newInstructions), "a"))
}

private fun parseWires(instructions: List<String>): MutableList<WireDescription> {
    val parsedInstructions: MutableList<WireDescription> = ArrayList()
    instructions.forEach { instruction ->
        if (instruction.matches(Regex("[0-9a-z]+ -> [a-z]+"))) {
            val instr = instruction.split(" -> ") // expr[0] expr[1]
            parsedInstructions.add(Pair(instr[1],
                    WireFunction("ASSERT", asList(instr[0]))))
        } else if (instruction.matches(Regex("NOT [0-9a-z]+ -> [a-z]+"))) {
            val instr = instruction.split(" ") // NOT[0] expr[1] ->[2] expr[3]
            parsedInstructions.add(Pair(instr[3],
                    WireFunction("NOT", asList(instr[1]))))
        } else { //Regex("[0-9a-z]+ (AND|OR|RSHIFT|LSHIFT) -> [a-z]+")
            val instr = instruction.split(" ") // expr[0] name[1] expr[2] ->[3] expr[4]
            parsedInstructions.add(Pair(instr[4], WireFunction(instr[1], asList(instr[0], instr[2]))))
        }
    }
    return parsedInstructions
}

private fun findWireSignal(instructions: MutableList<WireDescription>, wire: String): Int {
    val values = HashMap<String, Int>()
    while (instructions.isNotEmpty()) {
        val expr = instructions.removeAt(0)
        val isApplicable = expr.second.args.all { isNumeric(it) || values.containsKey(it) }
        if (isApplicable) {
            val args = expr.second.args.map { if (values.contains(it)) values[it] else it.toInt() }
            val result = when (expr.second.name) {
                "ASSERT" -> args[0]
                "NOT" -> args[0]?.inv()?.and(0xffff)
                "LSHIFT" -> args[0]?.shl(args[1] ?: 0)?.and(0xffff)
                "RSHIFT" -> args[0]?.shr(args[1] ?: 0)
                "AND" -> args[0]?.and(args[1] ?: 0)
                "OR" -> args[0]?.or(args[1] ?: 0)
                else -> throw IllegalStateException("Wrong pattern")
            }
            values.put(expr.first, result ?: -1)
        } else {
            instructions.add(expr)
        }
    }
    return values.getOrDefault(wire, -1)
}

private fun isNumeric(input: String): Boolean = input.toCharArray().all(Char::isDigit)

private data class WireFunction(val name: String, val args: List<String>)

private typealias WireDescription = Pair<String, WireFunction>