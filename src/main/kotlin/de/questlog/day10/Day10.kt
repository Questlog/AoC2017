package de.questlog.day10

import java.util.Arrays.asList

data class Day10Result(
    val multiplication : Int,
    val denseHash : String
)

data class SkipSize(
    var size : Int,
    var skip : Int = 0,
    var currentPos : Int = 0
) {
    fun increase(skipSize : Int) {
        currentPos = (currentPos + skip + skipSize) % size
        skip += 1
    }
}

fun solveSingleRound(numbersSize : Int, puzzleInput : String) : Day10Result {
    val skipList = puzzleInput.split(",").map { it.trim().toInt() }

    return solve(skipList, 1, numbersSize)
}

fun solveFull(plainText : String) : Day10Result{
    return solve(
        //convert to byte array, add suffix
        plainText.map { it.toByte().toInt() }.plus( asList(17, 31, 73, 47, 23) ),
        64
    )
}

fun solve(key : List<Int>, rounds : Int, internalSize : Int = 255) : Day10Result {
    val data = (0..internalSize).map { it }.toMutableList()
    val skipSize = SkipSize(internalSize+1)

    repeat(rounds){
        knotHashRound(data, key, skipSize)
    }

    val denseHash = reduceToDenseHash(data)
    val hashString = denseHash.joinToString(separator = "") {
        var hex = Integer.toHexString(it)
        if(hex.length < 2) //"a" -> "0a"
            hex = "0" + hex
        hex
    }

    return Day10Result(data[0] * data[1], hashString)
}

fun knotHashRound(data : MutableList<Int>, key : List<Int>, skipSize: SkipSize) {
    for (it in key) {
        if(it == 0) {
            skipSize.increase(0)
            continue
        }
        val subList = data.subListCycled(skipSize.currentPos, skipSize.currentPos + it - 1)
        subList.reverse()
        data.replaceRangeCycle(skipSize.currentPos, subList)
        skipSize.increase(it.toInt())
    }
}

fun reduceToDenseHash(sparseHash : List<Int>) : List<Int> {
    return sparseHash.windowed(16, 16, false).map { it.reduce { acc, i -> acc xor i } }
}

private fun <E> MutableList<E>.subListCycled(start: Int, end: Int) : MutableList<E> {
    if(start > this.size) throw IndexOutOfBoundsException("Start can't be greater than list size")
    val realEnd = end % this.size

    return when {
        realEnd < 0      -> ArrayList()
        start <= realEnd -> this.mutableSubList(start, realEnd)
        else             -> this.mutableSubList(start, this.size - 1).also { it.addAll(this.mutableSubList(0, realEnd)) }
    }
}

private fun <E> MutableList<E>.mutableSubList(fromIndex: Int, toIndex: Int): MutableList<E> = MutableList(toIndex - fromIndex + 1) { this[fromIndex + it] }

private fun <E> MutableList<E>.replaceRangeCycle(start : Int, replacement: List<E>) {
    if(replacement.size > this.size)
        throw IndexOutOfBoundsException("Replacement List cant be larger then target list")
    if(replacement.isEmpty())
        return
    for(i in start until start + replacement.size)
        this[i % this.size] = replacement[i - start]
}
