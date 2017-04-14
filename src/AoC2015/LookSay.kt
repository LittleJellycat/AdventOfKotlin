package AoC2015

fun main(args: Array<String>) {
    val input = "1113122113"
    var saidInput = input.toList()
    for (i in 0..51) {
        saidInput = applyGameRules(saidInput)
        println("$i ${saidInput.count()}")
    }
}

private fun applyGameRules(input: List<Char>): List<Char> {
    var stackOfToken = 0
    var lastToken = input.first()
    val outSequence = mutableListOf<Char>()
    input.forEach {
        if (it == lastToken) {
            stackOfToken++
        } else {
            outSequence.add(stackOfToken.toString().first())
            outSequence.add(lastToken)
            lastToken = it
            stackOfToken = 1
        }
    }
    lastToken = input.last()
    outSequence.add(stackOfToken.toString().first())
    outSequence.add(lastToken)

    return outSequence
}