package de.questlog

import org.junit.Assert
import org.junit.Test
import kotlin.test.assertEquals

class Day03Test {

    @Test
    fun solve() {

        assertEquals(2, de.questlog.day03.solve(23))
        assertEquals(3, de.questlog.day03.solve(12))
        assertEquals(31, de.questlog.day03.solve(1024))

        println(de.questlog.day03.solve(277678))
    }

    @Test
    fun solvePartTwo() {

        assertEquals(806, de.questlog.day03.solvePartTwo(747  ))
        assertEquals(4, de.questlog.day03.solvePartTwo(2))
        assertEquals(23, de.questlog.day03.solvePartTwo(11))

        println(de.questlog.day03.solvePartTwo(277678))
    }
}