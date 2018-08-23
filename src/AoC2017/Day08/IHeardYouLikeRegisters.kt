package AoC2017.Day08

import java.io.File

fun main(args: Array<String>) {
    val input = File(args[0]).readLines().map { it.split("\\s+".toRegex()) }
    println(executeRegisterCommands(input).maxBy { it.value })
    println(findMaxAtExecution(input))
}

private fun executeRegisterCommands(commands: List<List<String>>): Map<String, Int> {
    val registers = mutableMapOf<String, Int>()
    commands.forEach { command ->
        val register = command[0]
        val sign = if (command[1] == "inc") 1 else -1
        val value = command[2].toInt() * sign
        val registerToCompare = registers.getOrPut(command[4]) { 0 }
        val action = command[5]
        val valueToCompare = command[6].toInt()
        registers.getOrPut(register) { 0 }
        registers.computeIfPresent(register) { _, registerValue ->
            when (action) {
                "<" -> if (registerToCompare < valueToCompare) registerValue + value else registerValue
                ">" -> if (registerToCompare > valueToCompare) registerValue + value else registerValue
                ">=" -> if (registerToCompare >= valueToCompare) registerValue + value else registerValue
                "<=" -> if (registerToCompare <= valueToCompare) registerValue + value else registerValue
                "!=" -> if (registerToCompare != valueToCompare) registerValue + value else registerValue
                else -> if (registerToCompare == valueToCompare) registerValue + value else registerValue
            }
        }
    }
    return registers
}

private fun findMaxAtExecution(commands: List<List<String>>): Int {
    val registers = mutableMapOf<String, Int>()
    var max = Int.MIN_VALUE
    commands.forEach { command ->
        val register = command[0]
        val sign = if (command[1] == "inc") 1 else -1
        val value = command[2].toInt() * sign
        val registerToCompare = registers.getOrPut(command[4]) { 0 }
        val action = command[5]
        val valueToCompare = command[6].toInt()
        registers.getOrPut(register) { 0 }
        registers.computeIfPresent(register) { _, registerValue ->
            when (action) {
                "<" -> if (registerToCompare < valueToCompare) registerValue + value else registerValue
                ">" -> if (registerToCompare > valueToCompare) registerValue + value else registerValue
                ">=" -> if (registerToCompare >= valueToCompare) registerValue + value else registerValue
                "<=" -> if (registerToCompare <= valueToCompare) registerValue + value else registerValue
                "!=" -> if (registerToCompare != valueToCompare) registerValue + value else registerValue
                else -> if (registerToCompare == valueToCompare) registerValue + value else registerValue
            }
        }
        val current = registers[register] ?: Int.MIN_VALUE
        if (current > max) max = current
    }
    return max
}