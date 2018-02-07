package AoC2015.Day11

val alphabet = listOf('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k',
        'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z')

fun main(args: Array<String>) {
    val oldPassword = "hepxcrrq"
    println(generateNext(oldPassword))
    println(generateNext(generateNext(oldPassword)))
}

private fun generateNext(oldPassword: String): String =
        generateSequence(oldPassword.toMutableList(), ::iterate).drop(1).first(::check).joinToString("")

private fun iterate(password: MutableList<Char>): MutableList<Char> {
    var i = password.size - 1
    while (password[i] == 'z') {
        password[i] = 'a'
        i--
    }
    password[i] = alphabet[alphabet.indexOf(password[i]) + 1]
    return password
}

private fun check(password: MutableList<Char>): Boolean = checkPairs(password) && checkAscending(password)

private fun checkAscending(password: List<Char>): Boolean = password.windowed(3)
        .any { (first, second, third) ->
            second - first == 1 && third - second == 1
        }


private fun checkPairs(password: List<Char>): Boolean = password.zipWithNext().distinct()
        .count { it.first == it.second } > 1
