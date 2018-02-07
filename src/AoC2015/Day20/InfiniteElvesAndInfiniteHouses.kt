package AoC2015.Day20

fun main(args: Array<String>) {
    val endNumber = 29000000 //given input
    println(getLowestHouseNumber(endNumber))
    println(getBoundedLowestHouseNumber(endNumber))
}

private fun getLowestHouseNumber(endNumber: Int): Int {
    return generateSequence(1) { x -> x + 1 }.first { getNumberOfPresents(it) > endNumber }
}

private fun getNumberOfPresents(n: Int): Int = mutableListOf(n, 1).apply {
    (2..Math.sqrt(n.toDouble()).toInt()).forEach {
        if (n % it == 0) {
            add(it)
            if (it != n / it) {
                add(n / it)
            }
        }
    }
}.sumBy { it * 10 }


private fun getBoundedLowestHouseNumber(endNumber: Int): Int {
    return generateSequence(1) { x -> x + 1 }.first { getBoundedNumberOfPresents(it) > endNumber }
}

private fun getBoundedNumberOfPresents(n: Int): Int = mutableListOf(n, 1).apply {
    (2..Math.sqrt(n.toDouble()).toInt()).forEach {
        if (n % it == 0) {
            add(it)
            if (it != n / it) {
                add(n / it)
            }
        }
    }
}.filter { n / it <= 50 }.sumBy { it * 11 }
