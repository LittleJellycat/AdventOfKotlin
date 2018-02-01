import java.util.*

fun main(args: Array<String>) {
    val digits = Scanner(System.`in`).use { it.next().map { it.toInt() - 48 } }
    println(solveCaptcha(digits))
    println(solveCaptcha(digits, digits.size / 2))
}

private fun solveCaptcha(digits: List<Int>, n: Int = 1) = (0 until digits.size)
        .filter { digits[it] == digits[(it + n) % digits.size] }
        .sumBy { digits[it] }
