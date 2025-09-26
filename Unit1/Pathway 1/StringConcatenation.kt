// The '+' operator concatenate two strings, making total become "2030" instead of 50.
// Removing the String brackets "" turn the val type to Int, which solves the problem. 
// Also can explicitly declare val type as Int
fun main() {
    val numberOfAdults: Int = 20 
    val numberOfKids: Int = 30
    val total: Int = numberOfAdults + numberOfKids
    println("The total party size is: $total")
}