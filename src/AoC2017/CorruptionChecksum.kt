import java.nio.file.Files
import java.nio.file.Paths

fun main(args: Array<String>) {
    val ints = Files.readAllLines(Paths.get(" "))
            .map {
                it.replace(Regex("\\s+"), " ")
                        .split(" ")
                        .filter { it.isNotBlank() }.map { it.toInt() }
            }
    println(getMaxMinCheckSum(ints))
    println(getDivisibleCheckSum(ints))
}

private fun getMaxMinCheckSum(ints: List<List<Int>>): Int = ints.map { listOf((it.max() ?: 0) - (it.min() ?: 0)) }.flatten().sum()

private fun getDivisibleCheckSum(ints: List<List<Int>>): Int {
    var sum = 0
    ints.forEach {
        for (i in 0 until ints.first().size) {
            for (j in 0 until ints.first().size) {
                if (i != j && it[i] % it[j] == 0) {
                    sum += it[i] / it[j]
                    break
                }
            }
        }
    }
    return sum
}