package de.questlog.day09

import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

class Day09Test {


    private lateinit var puzzleInput: String

    @Before
    fun setUp() {
        puzzleInput = this::class.java.classLoader.getResource("Day09Input.txt").readText()
    }

    @Test
    fun solve() {

        assertEquals(StreamCounter(1, 0), solve("{}"))
        assertEquals(StreamCounter(6, 0), solve("{{{}}}"))
        assertEquals(StreamCounter(5, 0), solve("{{},{}}"))
        assertEquals(StreamCounter(16, 0), solve("{{{},{},{{}}}}"))
        assertEquals(StreamCounter(1, 4), solve("{<a>,<a>,<a>,<a>}"))
        assertEquals(StreamCounter(9, 8), solve("{{<ab>},{<ab>},{<ab>},{<ab>}}"))
        assertEquals(StreamCounter(9, 0), solve("{{<!!>},{<!!>},{<!!>},{<!!>}}"))
        assertEquals(StreamCounter(3, 17), solve("{{<a!>},{<a!>},{<a!>},{<ab>}}"))

        println(solve(puzzleInput))
    }

}
