package de.questlog

import org.junit.Test
import kotlin.test.assertEquals
import de.questlog.day07.solve
import org.junit.Before

class Day07Test {

    private lateinit var puzzleInput: String

    @Before
    fun setUp() {
        puzzleInput = this::class.java.classLoader.getResource("Day07Input.txt").readText()
    }

    @Test
    fun solve() {
        val testInput = "pbga (66)\n" +
                "xhth (57)\n" +
                "ebii (61)\n" +
                "havc (66)\n" +
                "ktlj (57)\n" +
                "fwft (72) -> ktlj, cntj, xhth\n" +
                "qoyq (66)\n" +
                "padx (45) -> pbga, havc, qoyq\n" +
                "tknk (41) -> ugml, padx, fwft\n" +
                "jptl (61)\n" +
                "ugml (68) -> gyxo, ebii, jptl\n" +
                "gyxo (61)\n" +
                "cntj (57)"

        assertEquals("tknk", solve(testInput).rootElementName)

        println(solve(puzzleInput))
    }

    @Test
    fun solvePartTwo() {
    }
}

private fun <E> MutableList<E>.add(vararg e: E) = e.forEach { this.add(it) }
