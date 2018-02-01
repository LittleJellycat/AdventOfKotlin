package AoC2015.Day14

import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

fun main(args: Array<String>) {
    val reindeers = Files.readAllLines(Paths.get(args[0]))
            .map { parseReindeer(it.split(" ")) }
    val time = 2503
    println(reindeers.map { reindeer -> reindeer.name to getDistance(time, reindeer) }
            .maxBy { it.second }?.second)
    println(reindeersResults(reindeers, time))
}

private fun reindeersResults(reindeers: List<Reindeer>, time: Int): Int? {
    val path = HashMap<Reindeer, Int>()
    val score = HashMap<Reindeer, Int>()
    for (reindeer in reindeers) {
        path[reindeer] = 0
        score[reindeer] = 0
    }
    for (t in 0 until time) {
        reindeers
                .filterNot { isResting(it, t) }
                .forEach { path.computeIfPresent(it) { _, value -> value + it.speed } }
        val leaders = path.entries.filter { it.value == path.values.max() }
        for (leader in leaders) {
            score.computeIfPresent(leader.key) { _, value -> value + 1 }
        }
    }
    return score.values.max()
}

private fun isResting(reindeer: Reindeer, time: Int): Boolean = (time % (reindeer.rest + reindeer.time) >= reindeer.time)


private fun getDistance(time: Int, reindeer: Reindeer): Int {
    var t = 0
    var dist = 0
    var flies = true
    var currentTime = 0
    while (t != time) {
        t++
        currentTime++
        if (!flies) {
            flies = currentTime == reindeer.rest
            if (flies) currentTime = 0
        } else {
            dist += reindeer.speed
            flies = currentTime != reindeer.time
            if (!flies) currentTime = 0
        }
    }
    return dist
}

private fun parseReindeer(info: List<String>): Reindeer = Reindeer(info[0], info[3].toInt(), info[6].toInt(), info[13].toInt())

data class Reindeer(val name: String, val speed: Int, val time: Int, val rest: Int)
