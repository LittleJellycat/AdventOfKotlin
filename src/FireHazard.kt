import java.io.File

/**
 * Created by Jelly on 24.08.2016.
 */

fun main(args: Array<String>) {
    val instructions = File("C:\\Users\\kotat\\input.txt").readLines().map { parseInstructions(it) }
    println(getLightsPattern(instructions))
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
    for (instruction in instructions) {
        val start: Pair<Int, Int> = instruction.second
        val end: Pair<Int, Int> = instruction.third
        for (i in start.first..end.first) {
            for (j in start.second..end.second) {
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