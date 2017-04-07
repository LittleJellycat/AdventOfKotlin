package AoC2015

import java.io.File
import java.util.*

fun main(args: Array<String>) {
    val containers = ArrayList<Int>()
    File("C:\\Users\\kotat\\Documents\\input17.txt").readLines()
            .forEach { containers.add(it.toInt()) }
    println(fill(containers))
}

fun fill(containers: List<Int>) { //AoC2015.fill 150L
    Collections.sort(containers)
//    val paths = Array<Int>(containers.size - 1)
//    for (i in 0..containers.size - 1) {
//        for (j in i..containers.size - 1)
//    }
}