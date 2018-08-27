package AoC2017.Day09

import java.io.File

fun main(args: Array<String>) {
    val input = File(args[0]).readText()
//    println(countScore(removeGarbage(removeCancelled(input))))
    println(countGarbage(removeCancelled(input)))
}

private fun removeCancelled(input: String): String {
    val sb = StringBuilder()
    var i = 0
    while (i < input.length) {
        if (input[i] != '!') {
            sb.append(input[i])
        } else {
            i++
        }
        i++
    }
    return sb.toString()
}

private fun removeGarbage(input: String): String {
    val sb = StringBuilder()
    var i = 0
    var isGarbage = false
    while (i < input.length) {
        val current = input[i]
        if (!isGarbage && current != '<') {
            sb.append(current)
        } else if (current == '<') {
            isGarbage = true
        } else if (current == '>') {
            isGarbage = false
        }
        i++
    }
    return sb.toString()
}

private fun countScore(input: String): Int {
    var total = 0
    var currentWeight = 0
    input.forEach {
        if (it == '{') {
            currentWeight++
        } else if (it == '}') {
            total += currentWeight
            currentWeight--
        }
    }
    return total
}

private fun countGarbage(input: String): Int {
    var count = 0
    var i = 0
    var isGarbage = false
    while (i < input.length) {
        val current = input[i]
        if (isGarbage && current != '>') {
            count++
        } else if (current == '<' && !isGarbage) {
            isGarbage = true
        } else if (current == '>') {
            isGarbage = false
        }
        i++
    }
    return count
}