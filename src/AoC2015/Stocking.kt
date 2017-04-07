package AoC2015

import java.io.File
import java.security.MessageDigest
import javax.xml.bind.DatatypeConverter

fun main(args: Array<String>) {
    val key = File("C:\\Users\\kotat\\input.txt").readText()
    println(findHashWithZeroes(key, 5)) //part 1
    println(findHashWithZeroes(key, 6)) //part 2
}

fun findHashWithZeroes(key: String, numberOfZeroes: Int): Int {
    return (1..Int.MAX_VALUE).first {
        DatatypeConverter.printHexBinary(
                MessageDigest.getInstance("MD5")
                        .digest((key + it).toByteArray())).startsWith("0".repeat(numberOfZeroes))
    }
}
