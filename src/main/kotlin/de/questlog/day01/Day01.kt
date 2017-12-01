package de.questlog.day01

fun solve(code : String) : Int {
    return (0 until code.length)
            .filter { code[it] == code[(it + 1) % code.length] }
            .map { code[it].toByte() - 48}
            .sum()
}

fun solvePartTwo(code : String) : Int {
    return (0 until code.length)
            .filter { code[it] == code[(it + code.length/2) % code.length] }
            .map { code[it].toByte() - 48 }
            .sum()
}
