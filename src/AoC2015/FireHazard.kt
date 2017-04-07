package AoC2015

import java.io.File


fun main(args: Array<String>) {
    val instructions = File("C:\\Users\\kotat\\input.txt").readLines().map { parseInstructions(it) }
    println(getLightsPattern(instructions))
    println(getNordicLightsPattern(instructions))
}

fun getNordicLightsPattern(instructions: List<Triple<String, Pair<Int, Int>, Pair<Int, Int>>>): Int {
    val lights: Array<IntArray> = Array(1000) { IntArray(1000) { 0 } }
    instructions.forEach {
        val start: Pair<Int, Int> = it.second
        val end: Pair<Int, Int> = it.third
        (start.first..end.first).forEach { i ->
            (start.second..end.second).forEach { j ->
                if (it.first == "on") {
                    lights[i][j]++
                } else if (it.first == "off") {
                    if(lights[i][j] != 0) {
                        lights[i][j]--
                    }
                } else { //toggle
                    lights[i][j] +=2
                }
            }
        }
    }
    return lights.flatMap { it.asIterable() }.sum()
}

fun parseInstructions(line: String): Triple<String, Pair<Int, Int>, Pair<Int, Int>> {
    val rawLineList = line.split("[\\s,]".toRegex())
    val parsedLine: Triple<String, Pair<Int, Int>, Pair<Int, Int>> = when (rawLineList[0]) {
        "turn" -> Triple(rawLineList[1], rawLineList[2].toInt() to rawLineList[3].toInt(), //turn off or turn on
                rawLineList[5].toInt() to rawLineList[6].toInt())
        else -> Triple(rawLineList[0], rawLineList[1].toInt() to rawLineList[2].toInt(), //toggle
                rawLineList[4].toInt() to rawLineList[5].toInt())
    }
    return parsedLine
}

fun getLightsPattern(instructions: List<Triple<String, Pair<Int, Int>, Pair<Int, Int>>>): Int {
    val lights: Array<BooleanArray> = Array(1000) { BooleanArray(1000) { false } }
    instructions.forEach { instruction ->
        val start: Pair<Int, Int> = instruction.second
        val end: Pair<Int, Int> = instruction.third
        (start.first..end.first).forEach { i ->
            (start.second..end.second).forEach { j ->
                if (instruction.first == "on") {
                    lights[i][j] = true
                } else if (instruction.first == "off") {
                    lights[i][j] = false
                } else { //toggle
                    lights[i][j] = !lights[i][j]
                }
            }
        }
    }
    return lights.flatMap { it.asIterable() }.count{it}
}