package AoC2015.Day24

fun main(args: Array<String>) {
    val packages = listOf(1, 2, 3, 7, 11, 13, 17, 19, 23, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113)
    println(getQuantumEntanglement(packages, 3))
    println(getQuantumEntanglement(packages, 4))
}

private fun getQuantumEntanglement(packages: List<Int>, n: Int): Long? {
    val midWeight = packages.sum() / n
    val subsets = mutableSetOf<List<Int>>()
    findSublistOfWeight(midWeight, packages, subsets, listOf())
    val minLength = subsets.minBy { it.size }?.size
    return subsets.filter { it.size == minLength }.map { it.foldRight(1L, { x, y -> x * y }) }.min()
}

private fun findSublistOfWeight(weight: Int, packages: List<Int>, subset: MutableSet<List<Int>>, sublist: List<Int>) {
    if (weight == 0) {
        subset.add(sublist)
        return
    }
    if (weight < 0 || packages.isEmpty()) {
        return
    }
    findSublistOfWeight(weight - packages.first(), packages.drop(1), subset, sublist + packages.first())
    findSublistOfWeight(weight, packages.drop(1), subset, sublist)
}
