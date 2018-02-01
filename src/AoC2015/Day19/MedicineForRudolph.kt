package AoC2015.Day19

import java.nio.file.Files
import java.nio.file.Paths

var minSteps = Int.MAX_VALUE

fun main(args: Array<String>) {
    val dict = Files.readAllLines(Paths.get(args[0])).map { it.split(" => ") }.map { it[0] to it[1] }
    val molecule = Files.readAllLines(Paths.get(args[1]))[0].split("(?<!^)(?=[A-Z])".toRegex()).toMutableList()
    println(calculateMolecules(dict, molecule))
    println(findNumberOfTransformations(dict, molecule))
}

private fun calculateMolecules(dict: List<Pair<String, String>>, molecule: MutableList<String>): Int {
    val moleculeSet = mutableSetOf<String>()
    dict.forEach { e ->
        val positions = mutableSetOf<Int>()
        molecule.forEachIndexed { i, _ ->
            if (molecule[i] == e.first) {
                positions.add(i)
            }
        }
        positions.forEach { p ->
            molecule[p] = e.second
            moleculeSet.add(molecule.joinToString(""))
            molecule[p] = e.first
        }
    }
    return moleculeSet.size
}

private fun findNumberOfTransformations(dict: List<Pair<String, String>>, molecule: MutableList<String>): Int? {
    dict.forEach {
        transform(dict, molecule.joinToString(""), 0, it)
    }
    return minSteps
}


//This seems to be probabilistic, just take the first successful one
private fun transform(dict: List<Pair<String, String>>, molecule: String, iterations: Int,
                      replacement: Pair<String, String>) {
    if (molecule == "e") {
        if (iterations < minSteps) {
            minSteps = iterations
        }
        return
    }
    if (!molecule.contains(replacement.second)) {
        return
    }
    dict.forEach {
        transform(dict, molecule.replaceFirst(replacement.second, replacement.first), iterations + 1, it)
    }
}