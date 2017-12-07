package de.questlog.day05

fun solve(instructions: String) : Int {
    return solve(instructions.split("\n").filterNot { it.isEmpty() })
}

fun solve(instructions : List<String>): Int {
    val program = instructions.map { Integer.parseInt(it) }.toMutableList()
    var pc = 0
    var counter = 0
    while (pc < instructions.size) {
        val opc = pc
        pc += program[pc]
        program[opc] += 1
        counter += 1
    }
    return counter
}

fun solvePartTwo(instructions: String) : Int {
    return solvePartTwo(instructions.split("\n").filterNot { it.isEmpty() })
}

fun solvePartTwo(instructions : List<String>): Int {
    val program = instructions.map { Integer.parseInt(it) }.toMutableList()
    var pc = 0
    var counter = 0
    while (pc < instructions.size) {
        val opc = pc
        val offset = program[pc]
        pc += offset

        program[opc] += if (offset < 3) 1 else -1
        counter += 1
    }
    return counter
}
