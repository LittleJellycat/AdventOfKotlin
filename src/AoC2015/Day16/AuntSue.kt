package AoC2015.Day16

import java.nio.file.Files
import java.nio.file.Paths

fun main(args: Array<String>) {
    val sues = Files.readAllLines(Paths.get(args[0])).map(::getSue)

    println(sues.filter { it.children == null || it.children == 3 }
            .filter { it.cats == null || it.cats == 7 }
            .filter { it.samoyeds == null || it.samoyeds == 2 }
            .filter { it.pomeranians == null || it.pomeranians == 3 }
            .filter { it.akitas == null || it.akitas == 0 }
            .filter { it.vizslas == null || it.vizslas == 0 }
            .filter { it.goldfish == null || it.goldfish == 5 }
            .filter { it.trees == null || it.trees == 3 }
            .filter { it.cars == null || it.cars == 2 }
            .filter { it.perfumes == null || it.perfumes == 1 }
    )
    println(sues.filter { it.children == null || it.children == 3 }
            .filter { it.cats == null || it.cats > 7 }
            .filter { it.samoyeds == null || it.samoyeds == 2 }
            .filter { it.pomeranians == null || it.pomeranians < 3 }
            .filter { it.akitas == null || it.akitas == 0 }
            .filter { it.vizslas == null || it.vizslas == 0 }
            .filter { it.goldfish == null || it.goldfish < 5 }
            .filter { it.trees == null || it.trees > 3 }
            .filter { it.cars == null || it.cars == 2 }
            .filter { it.perfumes == null || it.perfumes == 1 }
    )
}

fun getSue(s: String): Sue {
    val params = s.split(" ").map { it.replace(":", "").replace(",", "") }
    val getParam = { param: String -> if (params.indexOf(param) != -1) params[params.indexOf(param) + 1].toInt() else null }
    val n = params[1].toInt()
    val children = getParam("children")
    val cats = getParam("cats")
    val samoyeds = getParam("samoyeds")
    val pomeranians = getParam("pomeranians")
    val akitas = getParam("akitas")
    val vizslas = getParam("vizslas")
    val goldfish = getParam("goldfish")
    val trees = getParam("trees")
    val cars = getParam("cars")
    val perfumes = getParam("perfumes")
    return Sue(n, children, cats, samoyeds, pomeranians, akitas, vizslas, goldfish, trees, cars, perfumes)
}


data class Sue(val n: Int,
               val children: Int?,
               val cats: Int?,
               val samoyeds: Int?,
               val pomeranians: Int?,
               val akitas: Int?,
               val vizslas: Int?,
               val goldfish: Int?,
               val trees: Int?,
               val cars: Int?,
               val perfumes: Int?)