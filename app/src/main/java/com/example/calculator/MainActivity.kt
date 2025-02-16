package com.example.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculator.ui.theme.CalculatorTheme
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.cos
import kotlin.math.ln
import kotlin.math.log10
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.math.tan

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Greeting(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}


@Composable
fun Greeting(modifier: Modifier = Modifier) {
    //input and history
    var input by remember { mutableStateOf("") }
    var rad: Boolean by remember { mutableStateOf(true) }
    var history = remember { mutableStateListOf<CalculationHistory>() }
    var showHistory by remember { mutableStateOf(false) }

    fun addHistory(expression: String, result: String) {
        val newHistory = CalculationHistory(expression, result)
        history =
            (listOf(newHistory) + history).take(5) as SnapshotStateList<CalculationHistory> // Keep only the last 5 entries
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.3f)
                    .background(color = Color.Black)
                    .padding(16.dp),
                contentAlignment = Alignment.BottomEnd
            ) {
                Text(
                    text = input,
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.End
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(color = Color.Black)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        CalculatorButton("sin") { input = newInput(input,"sin(") }
                        CalculatorButton("cos") { input = newInput(input,"cos(") }
                        CalculatorButton("tan") { input = newInput(input,"tan(") }
                        CalculatorButton("History") { showHistory = !showHistory }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        CalculatorButton(if (rad) "rad" else "deg") { rad = !rad }
                        CalculatorButton("log") { input = newInput(input,"log(") }
                        CalculatorButton("ln")  { input = newInput(input,"ln(") }
                        CalculatorButton("(")   { input = newInput(input,"(") }
                        CalculatorButton(")")   { input = newInput(input,")") }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        CalculatorButton("!")   { input = newInput(input,"!") }
                        CalculatorButton("%")   { input = newInput(input,"%") }
                        CalculatorButton("AC")  { input = "" }
                        CalculatorButton("⌫")   {
                            if (input.isNotEmpty() && input != "") {
                                input = input.dropLast(1)
                                if (input.isEmpty()) input = ""
                            }
                        }
                        CalculatorButton("÷")   { input = newInput(input, "/") }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        CalculatorButton("^")  { input = newInput(input,"^") }
                        CalculatorButton("7")  { input = newInput(input,"7") }
                        CalculatorButton("8")  { input = newInput(input,"8") }
                        CalculatorButton("9")  { input = newInput(input,"9") }
                        CalculatorButton("×")  { input = newInput(input,"×") }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        CalculatorButton("√") { input = newInput(input,"√") }
                        CalculatorButton("4") { input = newInput(input,"4") }
                        CalculatorButton("5") { input = newInput(input,"5") }
                        CalculatorButton("6") { input = newInput(input,"6") }
                        CalculatorButton("-") { input = newInput(input,"-") }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        CalculatorButton("π") { input = newInput(input,"π") }
                        CalculatorButton("1") { input = newInput(input,"1") }
                        CalculatorButton("2") { input = newInput(input,"2") }
                        CalculatorButton("3") { input = newInput(input,"3") }
                        CalculatorButton("+") { input = newInput(input,"+") }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        CalculatorButton("e")  { input = newInput(input,"e") }
                        CalculatorButton("00") { input = newInput(input,"00")}
                        CalculatorButton("0")  { input = newInput(input,"0") }
                        CalculatorButton(".")  { input = newInput(input,".") }
                        CalculatorButton("=")  { val result = calculateResult(input, rad)
                            if (result != "Error") {
                                history.add(CalculationHistory(input, result)) // Add to history
                            }
                            input = result }
                    }
                    if (showHistory) {
                        HistoryScreen(history)
                    }
                }
            }
        }
    }
}

@Composable
fun HistoryScreen(history: List<CalculationHistory>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(color = Color.DarkGray, shape= CircleShape)

    ) {
        Text(
            text = "History",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        history.forEach { item ->
            Text(
                text = "${item.expression} = ${item.result}",
                fontSize = 16.sp,
                color = Color.White,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
}

@Composable
fun CalculatorButton(label: String, onClick: () -> Unit) {
    val buttonColor = when (label) {
        "AC", "⌫" -> Color(0xFF727275)
        "=", "+", "-", "×", "÷" -> Color(0xFF1824D7)
        else -> Color.DarkGray
    }

    Box(
        modifier = Modifier
            .size(60.dp) // Control the size of each button
            .background(buttonColor, shape = CircleShape)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

data class CalculationHistory(
    val expression: String,
    val result: String
)

fun newInput(currentInput: String, newInput: String): String {
    val operators = setOf("+", "-", "×", "/", "^", "%", "!")
    val numericvalues = setOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "0","e","π")

    return when {
        //only allow "-" at start
        currentInput.isEmpty() -> if (newInput in operators && newInput != "-") "" else newInput

        //ad multiply after closing bracket when input is number
        newInput in numericvalues && currentInput.last() == ')' -> "$currentInput×$newInput"

        //multiply consecutive pi or e inputs if prevvalues are numeric
        (currentInput in numericvalues) && (newInput == "π" || newInput == "e" ) -> "$currentInput×$newInput"

        //add multply symbol when pi or e before and input is number
        (currentInput in setOf("π","e")) && (newInput in numericvalues) -> "$currentInput×$newInput"

        //replace op when another is clicked
        operators.contains(currentInput.last().toString()) && newInput in operators ->
            currentInput.dropLast(1) + newInput

        // dont add many decmals
        newInput == "." && currentInput.takeLastWhile { it.isDigit() || it == '.' }.contains(".") ->
            currentInput

        // factorial add only after a no. or ")"
        newInput == "!" && (currentInput.isEmpty() || !currentInput.last().isDigit() && currentInput.last() != ')') ->
            currentInput

        // dont add factorial after decimal
        newInput == "." && currentInput.lastOrNull() == '!' -> currentInput

        // dont add multi factorials
        newInput == "!" && currentInput.last() == '!' -> currentInput

        // dont add "(" after number but 5x(...
        newInput == "(" && currentInput.isNotEmpty() && currentInput.last().isDigit() -> "$currentInput×$newInput"
        //suggested by studio mine was currentInput + "×" + newInput

        // dont add closing parenthesis at the start or without previous matching "("
        newInput == ")" && (currentInput.count { it == '(' } <= currentInput.count { it == ')' }) -> currentInput

        currentInput.last().isDigit() && (newInput == "log(" || newInput == "ln(") -> "$currentInput×$newInput"

        else -> currentInput + newInput
    }
}

fun calculateResult(input: String,rad: Boolean): String {
    if (input.isEmpty()) return ""

    try {
        val result = evaluateExpression(input,rad)
        return formatResult(result)
    } catch (e: Exception) {
        return "Error"
    }
}

fun evaluateExpression(expression: String,rad: Boolean): Double {
    var modifiedExpression = expression
        .replace("×", "*")
        .replace("÷", "/")
        .replace("π", Math.PI.toString())
        .replace("e", Math.E.toString())

    val openParenthesesCount = modifiedExpression.count { it == '(' }
    val closeParenthesesCount = modifiedExpression.count { it == ')' }

    // add closing brackets to avoid errors
    if (openParenthesesCount > closeParenthesesCount) {
        modifiedExpression += ")".repeat(openParenthesesCount - closeParenthesesCount)
    }

    modifiedExpression = handleImplicitMultiplication(modifiedExpression)
    modifiedExpression = handleFunctions(modifiedExpression,rad)
    modifiedExpression = handleFactorial(modifiedExpression)
    modifiedExpression = handlePercentage(modifiedExpression)
    modifiedExpression = handlePower(modifiedExpression)
    modifiedExpression = handleSquareRoot(modifiedExpression)



    //used chatGPT for below code------------------->
    return object : Any() {
        var pos = -1
        var ch = 0
        fun nextChar() {
            ch = if (++pos < modifiedExpression.length) modifiedExpression[pos].code else -1
        }

        fun eat(charToEat: Int): Boolean {
            while (ch == ' '.code) nextChar()
            if (ch == charToEat) {
                nextChar()
                return true
            }
            return false
        }

        fun parse(): Double {
            nextChar()
            val x = parseExpression()
            if (pos < modifiedExpression.length) throw RuntimeException("Unexpected: " + ch.toChar())
            return x
        }

        fun parseExpression(): Double {
            var x = parseTerm()
            while (true) {
                if (eat('+'.code)) x += parseTerm()
                else if (eat('-'.code)) x -= parseTerm()
                else return x
            }
        }

        fun parseTerm(): Double {
            var x = parseFactor()
            while (true) {
                if (eat('*'.code)) x *= parseFactor()
                else if (eat('/'.code)) x /= parseFactor()
                else return x
            }
        }

        fun parseFactor(): Double {
            if (eat('+'.code)) return parseFactor()
            if (eat('-'.code)) return -parseFactor()
            var x: Double
            val startPos = pos
            if (eat('('.code)) {
                x = parseExpression()
                eat(')'.code)
            } else if (ch >= '0'.code && ch <= '9'.code || ch == '.'.code) {
                while (ch >= '0'.code && ch <= '9'.code || ch == '.'.code) nextChar()
                x = modifiedExpression.substring(startPos, pos).toDouble()
            } else {
                throw RuntimeException("Unexpected: " + ch.toChar())
            }
            return x
        }
    }.parse()
    //<----------------------------

}

//calculate functions
fun handleFunctions(expression: String, rad: Boolean): String {
    var modifiedExpression = expression
    val functions = listOf("sin", "cos", "tan", "log", "ln")

    for (function in functions) {
        while (modifiedExpression.contains("$function(")) {
            val startIndex = modifiedExpression.indexOf("$function(")
            val endIndex = findClosingParenthesis(modifiedExpression, startIndex + function.length)

            val innerExpression =
                modifiedExpression.substring(startIndex + function.length + 1, endIndex)
            val innerResult = evaluateExpression(innerExpression, rad)

            val result = when (function) {
                "sin" -> if (rad) sin(innerResult) else sin(degreesToRadians(innerResult))
                "cos" -> if (rad) cos(innerResult) else cos(degreesToRadians(innerResult))
                "tan" -> if (rad) tan(innerResult) else tan(degreesToRadians(innerResult))
                "log" -> log10(innerResult)
                "ln" -> ln(innerResult)
                else -> throw RuntimeException("Unknown function: $function")
            }

            modifiedExpression = modifiedExpression.replaceRange(startIndex, endIndex + 1, result.toString())
        }
    }
    return modifiedExpression
}

fun handleFactorial(expression: String): String {
    var modifiedExpression = expression
    val regex = "(\\d+)!".toRegex()

    regex.findAll(modifiedExpression).forEach { matchResult ->
        val number = matchResult.groupValues[1].toInt()
        val factorialResult = factorial(number)

        modifiedExpression = modifiedExpression.replace(matchResult.value, factorialResult.toString())
    }
    return modifiedExpression
}

fun handlePercentage(expression: String): String {
    var modifiedExpression = expression
    val regex = "(\\d+(\\.\\d+)?)%".toRegex()

    regex.findAll(modifiedExpression).forEach { matchResult ->
        val number = matchResult.groupValues[1].toDouble()
        val percentageResult = number / 100.0

        modifiedExpression = modifiedExpression.replace(matchResult.value, percentageResult.toString())
    }
    return modifiedExpression
}

fun handleImplicitMultiplication(expression: String): String {
    var modifiedExpression = expression
    val functions = listOf("sin", "cos", "tan", "log", "ln", "√")

    for (i in 1 until modifiedExpression.length) {
        if (modifiedExpression[i].isLetter() || modifiedExpression[i] == '(' || modifiedExpression[i] == '√') {
            if (modifiedExpression[i - 1].isDigit() || modifiedExpression[i - 1] == ')') {
                modifiedExpression = modifiedExpression.substring(0, i) + "*" + modifiedExpression.substring(i)
            }
        }
    }
    return modifiedExpression
}

fun handlePower(expression: String): String {
    var modifiedExpression = expression
    val regex = "(\\d+(\\.\\d+)?)\\^(\\d+(\\.\\d+)?)".toRegex()

    regex.findAll(modifiedExpression).forEach { matchResult ->
        val base = matchResult.groupValues[1].toDouble()
        val exponent = matchResult.groupValues[3].toDouble()
        val powerResult = base.pow(exponent)

        modifiedExpression = modifiedExpression.replace(matchResult.value, powerResult.toString())
    }
    return modifiedExpression
}

fun handleSquareRoot(expression: String): String {
    var modifiedExpression = expression
    val regex = "√(\\d+(\\.\\d+)?)".toRegex()

    regex.findAll(modifiedExpression).forEach { matchResult ->
        val number = matchResult.groupValues[1].toDouble()
        if (number < 0) throw RuntimeException("Square root of a negative number is not defined")
        val sqrtResult = sqrt(number)

        modifiedExpression = modifiedExpression.replace(matchResult.value, sqrtResult.toString())
    }
    return modifiedExpression
}

fun findClosingParenthesis(expression: String, startIndex: Int): Int {
    var openCount = 1
    for (i in startIndex + 1 until expression.length) {
        when (expression[i]) {
            '(' -> openCount++
            ')' -> openCount--
        }
        if (openCount == 0) return i
    }
    return -1
}

fun factorial(n: Int): Int {
    return if (n == 0) 1 else (1..n).reduce { acc, i -> acc * i }
}

fun formatResult(result: Double): String {
    val bd = BigDecimal(result).setScale(10, RoundingMode.HALF_UP)
    return if (bd.remainder(BigDecimal.ONE).compareTo(BigDecimal.ZERO) == 0) {
        bd.toInt().toString()
    } else {
        bd.stripTrailingZeros().toPlainString()
    }
}

fun degreesToRadians(degrees: Double): Double {
    return degrees * (Math.PI / 180.0)
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CalculatorTheme {
        Greeting()
    }
}
