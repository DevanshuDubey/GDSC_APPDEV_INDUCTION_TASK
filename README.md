# 📱 Calculator Application

A powerful and easy-to-use calculator application built with **Jetpack Compose**. This application supports both **basic arithmetic** and **scientific calculations** with a user-friendly interface.

## ✨ Features

### 🧮 Level 1: Basic Calculator
Perform essential arithmetic operations with ease:

- ➕ **Addition (+)**
- ➖ **Subtraction (-)**
- ✖️ **Multiplication (*)**
- ➗ **Division (/)**

### 🔬 Level 2: Scientific Calculator
Unlock advanced mathematical operations:

- 📐 **Trigonometric Functions**: `sin()`, `cos()`, `tan()`
- 📊 **Logarithmic Functions**: `log()`, `ln()`
- 📈 **Power and Root Calculations**: `x^y`, `√x`
- 🎯 **Factorial Calculation**: `n!`

## 📖 Usage

### Basic Calculator:
1. Enter numbers and select the arithmetic operation.
2. Press the **"="** button to view the result.

### Scientific Calculator:
1. Access advanced functions like trigonometry and logarithmic calculations.
2. Perform complex operations seamlessly with intuitive input.

## App Preview
### 📸 Demo Video
### 📸 Screenshots

## 📂 Tech Stack

1. **Language**: Kotlin
2. **Framework**: Jetpack Compose
3. **Environment**: Android Studio


## 🛠️ Features and Functionalities

### 1. **User Interface (UI)**

- **`MainActivity`**: Entry point of the application where the `CalculatorUI()` Composable is called.
- **`CalculatorUI()`**: Main calculator layout containing:
  - Display Box: Shows the user input and results.
  - Button Grid: Includes all calculator buttons (numbers, operators, and scientific functions).

### 2. **Calculator Buttons**

- **`CalculatorButton()`**: A reusable Composable function to create buttons.
  - Parameters:
    - `label`: Button text (e.g., "+", "sin", "AC")
    - `onClick`: Action performed on button click

- Button Categories:
    - **Basic Operations**: "+", "-", "×", "÷"
    - **Scientific Operations**: `sin`, `cos`, `tan`, `log`, `ln`, `√`, `!`, `%`, `^`
    - **Special Functions**: `AC` (All Clear), `⌫` (Backspace), `=` (Evaluate)

### 3. **Input Handling**

- **`newInput()`**: Validates and updates user input.
  - Ensures correct operator usage (e.g., prevents multiple decimals).
  - Automatically multiplies constants like π or e when required.
  - Handles bracket and other edge cases.

### 4. **Expression Evaluation**

- **`calculateResult()`**: Parses and evaluates mathematical expressions.

- **`evaluateExpression()`**: Core parsing logic to compute results.
  - Replaces custom symbols (×, ÷, π, e) with standard equivalents.
  - Auto-closes open parentheses.
  - Supports chained operations.

### 5. **Mathematical Function Processing**

- **`handleFunctions()`**: Processes trigonometric and logarithmic functions.
  - Supports both Radians and Degrees for `sin`, `cos`, and `tan`.

- **`handleFactorial()`**: Calculates the factorial of a number.

- **`handlePower()`**: Evaluates exponentiation (e.g., `2^3`).

- **`handleSquareRoot()`**: Computes square roots.

- **`handlePercentage()`**: Converts percentages to decimals.

### 6. **Utility Functions**

- **`findClosingParenthesis()`**: Finds the matching closing parenthesis for expressions.

- **`formatResult()`**: Formats the output, removing trailing zeros for clean display.

- **`degreesToRadians()`**: Converts degrees to radians for trigonometric calculations.

### 7. **Error Handling**

- Built-in error checks prevent invalid calculations (e.g., division by zero).
- Provides a `Error` message for invalid inputs.

## 📣 Additional Notes

- Ensure you switch between Radians and Degrees using the **Rad/Deg** toggle.
- Factorials are supported only for positive integers.
- Invalid inputs return an `Error` message.


## 📦 Installation

Download and install the latest release from GitHub:

[![Get it on GitHub](https://github.com/DevanshuDubey/GDSC_APPDEV_INDUCTION_TASK/blob/master/badge_github.png)](https://github.com/DevanshuDubey/GDSC_APPDEV_INDUCTION_TASK/releases/latest)

## 🛠️ Requirements
- **Android Studio**: Latest version recommended
- **Android Device/Emulator**: API Level 21+

## 📧 Contact
For any queries or feedback, reach out via:
- GitHub: [DevanshuDubey](https://github.com/DevanshuDubey)


