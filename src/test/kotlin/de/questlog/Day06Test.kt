package de.questlog

import org.junit.Test
import kotlin.test.assertEquals

class Day06Test {

    private val puzzleInput = "14\t0\t15\t12\t11\t11\t3\t5\t1\t6\t8\t4\t9\t1\t8\t4"

    @Test
    fun solve() {
        assertEquals(5, de.questlog.day06.solve("0\t2\t7\t0"))

        println(de.questlog.day06.solve(puzzleInput))
    }

    @Test
    fun solvePartTwo() {
        assertEquals(4, de.questlog.day06.solvePartTwo("0\t2\t7\t0"))

        println(de.questlog.day06.solvePartTwo(puzzleInput))
    }
}