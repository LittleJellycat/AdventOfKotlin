package AoC2015

import java.util.*

/*This solution takes too long to calculate the answer. The output is correct though.
* I believe, there should be a much faster solution*/

fun main(args: Array<String>) {
    val endNumber = 29000000 //given input
    val numberOfPresentsList = ArrayList<Int>()
    var n = 1
    numberOfPresentsList.add(0, 0)
    while (true) {
        numberOfPresentsList.add(n, 0)
        (1..n)
                .filter { n % it == 0 }
                .forEach { numberOfPresentsList[n] += it * 10 }
        if (!numberOfPresentsList.filter { n >= endNumber }.isEmpty()) break
        n++
        if (numberOfPresentsList.max()!! >= endNumber) break
        if (n > endNumber / 10) break
    }
    println(numberOfPresentsList.indexOfFirst { it >= endNumber })
}