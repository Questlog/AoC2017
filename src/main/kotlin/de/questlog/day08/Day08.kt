package de.questlog.day08

import java.text.ParseException

enum class Condition {
    EQ,
    NEQ,
    GT,
    LT,
    GTE,
    LTE;

    companion object {
        public fun parse(str : String) : Condition{
            when (str) {
                "==" -> return Condition.EQ
                "!=" -> return Condition.NEQ
                ">" -> return Condition.GT
                "<" -> return Condition.LT
                ">=" -> return Condition.GTE
                "<=" -> return Condition.LTE
            }
            throw RuntimeException("Could not parse $str")
        }
    }

    fun evaluate(a : Int, b : Int) : Boolean{
        return when(this){
            EQ -> a == b
            NEQ -> a != b
            GT -> a > b
            LT -> a < b
            GTE -> a >= b
            LTE -> a <= b
        }
    }
}

enum class Method {
    INC, DEC;

    fun run(a : Int, b: Int) : Int {
        return when(this) {
            INC -> a + b
            DEC -> a - b
        }
    }
}

data class Instruction (
        val register : String,
        val method: Method,
        val value : Int,
        val conditionRegister : String,
        val condition : Condition,
        val conditionValue : Int
)


private fun String.parseInstruction(): Instruction {
    val parts = this.split(" ")
    println(parts)
    return Instruction(
        register = parts[0],
        method = Method.valueOf(parts[1].toUpperCase()),
        value = parts[2].toInt(),
        conditionRegister = parts[4],
        condition = Condition.parse(parts[5]),
        conditionValue = parts[6].toInt()
    )
}

private fun String.getInstructions() : List<Instruction> {
    return this.split("\n")
            .filter { !it.isEmpty() }
            .map { it.parseInstruction() }
}

data class Day08Result(
        val maxRegisterValueAtEnd : Int,
        val maxRegisterValueAtRuntime : Int
)

/*b inc 5 if a > 1
a inc 1 if b < 5
c dec -10 if a >= 1
c inc -20 if c == 10
*/
fun solve(puzzleInput : String) : Day08Result {
    val registers = HashMap<String, Int>()
    val instructions = puzzleInput.getInstructions()
    var maxValue = Int.MIN_VALUE
    instructions.forEach {
        val registerValue = registers.getOrPut(it.register, {0})
        val conditionRegisterValue = registers.getOrPut(it.conditionRegister, {0})
        if(it.condition.evaluate(conditionRegisterValue, it.conditionValue))
            registers.put(it.register, it.method.run(registerValue, it.value))
        if(registerValue > maxValue)
            maxValue = registerValue
    }

    return Day08Result(registers.values.max() ?: 0, maxValue)
}

