package de.questlog.day10

import org.junit.Test
import org.junit.Assert.*

class Day10Test {

    val puzzleInput = "94,84,0,79,2,27,81,1,123,93,218,23,103,255,254,243"

    @Test
    fun solve() {
        assertEquals(0, solveSingleRound(4, "0, 0, 0, 0").multiplication)
        assertEquals(12, solveSingleRound(4, "3, 4, 1, 5").multiplication)

        assertEquals(23715, solveSingleRound(255, puzzleInput).multiplication)
        println(solveSingleRound(255, puzzleInput))

        assertEquals("a2582a3a0e66e6e86e3812dcb672a272", solveFull("").denseHash)
        assertEquals("33efeb34ea91902bb2f59c9920caa6cd", solveFull("AoC 2017").denseHash)
        assertEquals("3efbe78a8d82f29979031a4aa0b16a9d", solveFull("1,2,3").denseHash)
        assertEquals("63960835bcdc130f0b66d7ff4f6a5a8e", solveFull("1,2,4").denseHash)

        println(solveFull(puzzleInput))
    }

}
