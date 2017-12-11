package de.questlog

import de.questlog.day08.Day08Result
import org.junit.Test
import kotlin.test.assertEquals
import de.questlog.day08.solve
import org.junit.Before
import kotlin.test.assertTrue

class Day08Test {

    private lateinit var puzzleInput: String

    @Before
    fun setUp() {
        puzzleInput = this::class.java.classLoader.getResource("Day08Input.txt").readText()
    }

    @Test
    fun solve() {
        val testInput = "b inc 5 if a > 1\n" +
                "a inc 1 if b < 5\n" +
                "c dec -10 if a >= 1\n" +
                "c inc -20 if c == 10"

        assertEquals(
                Day08Result(1, 10),
                solve(testInput)
        )

        println(solve(puzzleInput))
    }

}
