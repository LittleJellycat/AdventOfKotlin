import java.io.File
import java.util.regex.Pattern

/**
 * Created by Jelly on 23.08.2016.
 */

fun main(args: Array<String>) {
    val testStrings = File("C:\\Users\\kotat\\input.txt").readLines()
    println(testStrings.asSequence().filter { isNice(it)}.count())
}

fun isNice(str: String): Boolean {
    val vowels = "[aeiou].*?[aeiou].*?[aeiou]".toRegex() //bad regex I think
    val doubles = "(\\w)\\1".toRegex()
    val bads = "(ab)|(cd)|(pq)|(xy)".toRegex()
    return !str.contains(bads) && str.contains(vowels) && str.contains(doubles)
}