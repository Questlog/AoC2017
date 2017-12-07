package de.questlog

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.io.File
import java.io.InputStream
import kotlin.test.assertEquals

class Day05Test {

    private lateinit var puzzleInput : String

    @Before
    fun setUp() {
        //Because IntelliJ crashed after copy-pasting the puzzleInput
        puzzleInput = this::class.java.classLoader.getResource("Day05Input.txt").readText()
    }

    @Test
    fun solve() {
        val testData = "0\n" +
                "3\n" +
                "0\n" +
                "1\n" +
                "-3"

        assertEquals(5, de.questlog.day05.solve(testData))

        println(de.questlog.day05.solve(puzzleInput))
    }

    @Test
    fun solvePartTwo() {
        val testData = "0\n" +
                "3\n" +
                "0\n" +
                "1\n" +
                "-3"

        assertEquals(10, de.questlog.day05.solvePartTwo(testData))

        println(de.questlog.day05.solvePartTwo(puzzleInput))
    }
}
