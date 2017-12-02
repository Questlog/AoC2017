package de.questlog.day02


fun solve(numbers : String): Int {
    return numbers.split("\n").map {
        var min = Integer.MAX_VALUE
        var max = 0
        it.split(Regex("\\s")).map{Integer.parseInt(it)}.forEach {
            if(it > max) max = it
            if(it < min) min = it
        }
        max - min
    }.sum()
}

fun solvePartTwo(numbers : String): Int {
    return numbers.split("\n").map {
        it.split(Regex("\\s"))
            .map{Integer.parseInt(it)}
            .let { line ->
                var sum = 0
                line.forEachIndexed { i, n ->
                    line.forEachIndexed { j, n2 ->
                        if(i!=j && n%n2 == 0) sum += n/n2
                    }
                }
                sum
            }
    }.sum()
}