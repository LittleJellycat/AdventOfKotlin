package AoC2015.Day25

//To continue, please consult the code grid in the manual.  Enter the code at row 2947, column 3029.
fun main(args: Array<String>) {
    val row = 2947
    val column = 3029
    val index = findIndex(row, column)
    println(calculateCode(index))
}

private fun findIndex(row: Int, column: Int): Int {
    var i = 1
    var j = 1
    var index = 1
    var n = 2
    while (i != column) {
        index += n
        i++
        n++
    }
    n = column
    while (j != row) {
        index += n
        j++
        n++
    }
    return index
}

private fun calculateCode(index: Int): Int {
    val firstCode = 20151125
    val multiplier = 252533
    val divisor = 33554393
    var n = 2
    var code = firstCode.toLong()
    while (n <= index) {
        code = (code * multiplier) % divisor
        n++
    }
    return code.toInt()
}