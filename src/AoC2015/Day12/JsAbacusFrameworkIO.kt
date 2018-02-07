package AoC2015.Day12

import java.io.File

fun main(args: Array<String>) {
    val script = File(args[0]).readText()
    //println(findSum(script))
    println(findSum(removeRed(script)))
}

private fun findSum(script: String): Int = Regex("-?\\d+").findAll(script).sumBy { it.value.toInt() }

private fun removeRed(script: String): String {
    var s = script
    val pattern = Regex("\\{[^{}]*}")
    var matchResult = pattern.find(s)
    while (matchResult != null){
        val group = matchResult.value
        s = s.replaceFirst(group, if (group.matches(Regex(".*:\"red.*"))) "0" else findSum(group).toString())
        matchResult = pattern.find(s)
    }
    return s
}//= script.replace(Regex("\\{[^{}]*:\"red\".*\\}"), "")
// = script.replace(Regex("\\{[^{]+?(:\"red\")*}"), "")

//C:\Users\MMUSER\documents\aoc.txt

//private static String filterRed(String input) {
//    Pattern braces = Pattern.compile("\\{[^{}]*\\}");
//    Matcher matcher = braces.matcher(input);
//    while (matcher.find()) {
//        String group = matcher.group();
//        input = input.replace(group, group.matches(".*:\"red.*") ? "0" : String.valueOf(getSum(group)));
//        matcher = braces.matcher(input);
//    }
//    return input;
//}