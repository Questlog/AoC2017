package de.questlog.day06


public fun List<Int>.maxIndex(): Int {
    if(this.isEmpty())
        return 0
    var maxVal = Int.MIN_VALUE
    var maxI = 0
    this.forEachIndexed { index, value ->
        if(value > maxVal) {
            maxVal = value
            maxI = index
        }
    }
    return maxI
}

public fun MutableList<Int>.incrementBanks(startBank : Int, blocks : Int) : List<Int> {
    for(i in startBank+1..startBank+blocks)
        this[i%this.size] += 1
    return this
}

fun getAllStatesUntilLoop(banks: List<Int>) : List<List<Int>>{
    val states = ArrayList<List<Int>>()
    val currentState = ArrayList(banks)
    do {
        states.add(ArrayList(currentState))
        val bank = currentState.maxIndex()
        val blocks = currentState[bank]
        currentState[bank] = 0
        currentState.incrementBanks(bank, blocks)
    } while (currentState !in states)
    states.add(currentState)
    return states
}

fun solve(banks : String) : Int{
    return solve(banks.split('\t').map { it.toInt() })
}
fun solve(banks : List<Int>) : Int {
    return getAllStatesUntilLoop(banks).size - 1
}
fun solvePartTwo(banks : String) : Int{
    return solvePartTwo(banks.split('\t').map { it.toInt() })
}
fun solvePartTwo(banks : List<Int>) : Int {
    return getAllStatesUntilLoop(banks).let {
        it.size - it.indexOf(it.last()) - 1
    }
}