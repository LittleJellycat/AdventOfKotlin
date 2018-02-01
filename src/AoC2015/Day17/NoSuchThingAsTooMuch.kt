fun main(args: Array<String>) {
    val containers = listOf<Int>(43, 3, 4, 10, 21, 44, 4, 6, 47, 41, 34, 17, 17, 44, 36, 31, 46, 9, 27, 38)
    val chains = mutableListOf<List<Int>>()
    println(countStorage(150, containers))
    countMemoStorage(150, containers, listOf(), chains)
    val minChain = chains.minBy { it.size }?.size
    println(chains.filter { it.size == minChain }.size)
}

private fun countStorage(amount: Int, containers: List<Int>): Int {
    if (amount == 0) return 1
    if (amount < 0 || containers.isEmpty()) return 0
    return countStorage(amount - containers.first(), containers.drop(1)) +
            countStorage(amount, containers.drop(1))
}

private fun countMemoStorage(amount: Int, containers: List<Int>, chain: List<Int>, chains: MutableList<List<Int>>) {
    when {
        amount == 0 -> {
            chains.add(chain)
            return
        }
        amount < 0 || containers.isEmpty() -> return
        else -> {
            countMemoStorage(amount - containers.first(), containers.drop(1),
                    chain + containers.first(), chains)
            countMemoStorage(amount, containers.drop(1), chain, chains)
        }
    }
}