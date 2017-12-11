package de.questlog.day09

data class StringScan(
        val str : String,
        var ptr : Int
) {
    fun current() = str[ptr]
    fun next() = this.also { it.ptr += 1 }
}

data class StreamCounter (
        var score : Int,
        var garbageCount : Int
) {
    operator fun plusAssign(groupScore: StreamCounter) {
        score += groupScore.score
        garbageCount += groupScore.garbageCount
    }
}

fun solve(puzzleInput : String) : StreamCounter{
    return countGroupScore(StringScan(puzzleInput, 0))
}


fun countGroupScore(input : StringScan, depth : Int = 0, insideGarbage : Boolean = false) : StreamCounter {
    val streamCounter =  StreamCounter(depth, 0)
    loop@ while (input.ptr < input.str.length){
        var canceled = false
        when (input.current()) {
            '{' -> if(!insideGarbage) streamCounter += countGroupScore(input.next(), depth + 1)
            '}' -> if(!insideGarbage) return streamCounter
            '<' -> if(!insideGarbage) streamCounter.garbageCount += countGroupScore(input.next(), depth + 1, true).garbageCount
            '>' -> return streamCounter
            '!' -> canceled = true

        }
        input.ptr += 1
        if(canceled)
            input.ptr += 1
        else if(insideGarbage)
            streamCounter.garbageCount += 1
    }
    return streamCounter
}
