package AoC2015

import java.util.*

val alphabet = arrayOf('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k',
        'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z')

fun main(args: Array<String>) {
    val oldPassword = "hepxcrrq"
    var newPassword = oldPassword.toCharArray()

    newPassword.reverse()
    while (!check(newPassword)) {
        newPassword = iterate(newPassword)
    }
    newPassword.reverse()
    println(newPassword)
}

private fun iterate(password: CharArray): CharArray {
    var i = 0
    while (password[i] == 'z') {
        password[i] = 'a'
        i++
    }
    password[i] = alphabet[alphabet.indexOf(password[i]) + 1]
    return password
}

fun check(password: CharArray): Boolean {
    return checkPairs(password) && checkAscending(password)
}

fun checkAscending(password: CharArray): Boolean {
    password.reverse()
    val result = (0..alphabet.size - 3)
            .map { Arrays.copyOfRange(alphabet, it, it + 3) }
            .any {
                Collections.indexOfSubList(password.toList(),
                        it.toList()) > -1
            }
    password.reverse()
    return result
}

fun checkPairs(password: CharArray): Boolean {
    var letter: Char = '0'
    var pairedLetter = '0'
    var pairsFound = 0
    password.forEach {
        if ((it == letter) && (it != pairedLetter)) {
            pairedLetter = it
            pairsFound++
        } else letter = it
    }
    return pairsFound > 1
}


