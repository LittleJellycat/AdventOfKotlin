fun main(args: Array<String>) {
    val input = 347991
    println(calculateDistance(input))
}

private fun calculateDistance(cell: Int): Int {
    val end = Math.sqrt(generateSequence(1) { it + 1 }.map { it * it }.first { it >= cell && it % 2 != 0 }.toDouble()).toInt()
    val prev = end - 2
    val border = ((prev * prev + 1)..(end * end)).toList()
    val edges = listOf(border.last(), border[3 * end - 4], border[2 * end - 3], border[end - 2])
    return edges.map { Math.abs(it - end / 2 - cell) }
            .sorted().first() + end / 2
}