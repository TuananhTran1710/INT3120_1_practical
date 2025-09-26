fun main() {
    val firstNumber = 10
    val secondNumber = 5
    val thirdNumber = 8
    
    val result = add(firstNumber, secondNumber)
    val anotherResult = add(firstNumber, thirdNumber)

    println("$firstNumber + $secondNumber = $result")
    println("$firstNumber + $thirdNumber = $anotherResult")
   	
    val subRes1 = subtract(firstNumber, secondNumber)
    val subRes2 = subtract(firstNumber, thirdNumber)
    
    println("$firstNumber - $secondNumber = $subRes1")
    println("$firstNumber - $thirdNumber = $subRes2")
}

// Define add() function below this line
fun add(num1: Int, num2: Int): Int {
	return num1 + num2
}

// Return the result of subtracting num1 by num2
fun subtract(num1: Int, num2: Int): Int {
	return num1 - num2
}