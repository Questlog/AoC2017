package de.questlog.day03

import java.lang.Math.sqrt

fun solve(code : Int) : Int{
    var width = 1
    var state = 0
    var i = 1
    var x = 0
    var y = 0
    while (i < code - width) {
        state += 1
        if (state == 4) state = 0
        i += width
        if(state == 1) x += width
        if(state == 2) y += width
        if(state == 3) x -= width
        if(state == 0) y -= width
        if(state % 2 == 0) width += 1
    }

    if(state == 0) x += code - i
    if(state == 1) y += code - i
    if(state == 2) x -= code - i
    if(state == 3) y -= code - i

    return Math.abs(x) + Math.abs(y)
}

data class Square (val value : Int, val x : Int, val y : Int)

fun getN8(x: Int, y: Int, squares: List<Square>) : Int {
    var sum = 0

    for(xd in -1..1){
        for(yd in -1..1){
            if(xd == 0 && yd == 0)
                continue

            for(square in squares) {
                if(square.x == x - xd && square.y == y - yd)
                    sum += square.value
            }
        }
    }
    return sum
}

fun solvePartTwo(code : Int) : Int{
    var width = 1
    var state = 0
    var i = 1
    var x = 0
    var y = 0

    val squares = ArrayList<Square>()
    squares.add(Square(1, 0, 0))

    while (squares.last().value <= code) {
        state += 1
        if (state == 4) state = 0
        for(n in 1..width){
            if(state == 1) x += 1
            if(state == 2) y += 1
            if(state == 3) x -= 1
            if(state == 0) y -= 1
            val n8 = getN8(x, y, squares)
            squares.add(Square(n8, x, y))
            if(n8 > code)
                return n8
        }

        i += width
        if(state % 2 == 0) width += 1
    }

    return 1
}