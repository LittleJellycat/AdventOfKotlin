package AoC2015.Day15

fun main(args: Array<String>) {
    println(fitReceipt())
    println(fitReceiptWithCalories())
}

private fun fitReceipt(): Int {
    var bestScore = 0
    var currentScore: Int
    for (i in 0..100) {
        for (j in 0..100) {
            for (k in 0..100) {
                for (n in 0..100) {
                    if (i + j + k + n == 100) {
                        val capacity = 5 * i - 1 * j - n
                        val durability = -i + 3 * j - k
                        val flavor = 4 * k
                        val texture = 2 * n
                        currentScore = if (capacity > 0 && durability > 0) {
                            capacity * durability * flavor * texture
                        } else {
                            0
                        }
                        if (currentScore > bestScore) {
                            bestScore = currentScore
                        }

                    }
                }
            }
        }
    }
    return bestScore
}

private fun fitReceiptWithCalories(): Int {
    var bestScore = 0
    var currentScore: Int
    for (i in 0..100) {
        for (j in 0..100) {
            for (k in 0..100) {
                for (n in 0..100) {
                    if (i + j + k + n == 100) {
                        val capacity = 5 * i - 1 * j - n
                        val durability = -i + 3 * j - k
                        val flavor = 4 * k
                        val texture = 2 * n
                        val calories = 5 * i + j + 6 * k + 8 * n
                        currentScore = if (capacity > 0 && durability > 0 && calories == 500) {
                            capacity * durability * flavor * texture
                        } else {
                            0
                        }
                        if (currentScore > bestScore) {
                            bestScore = currentScore
                        }
                    }
                }
            }
        }
    }
    return bestScore
}