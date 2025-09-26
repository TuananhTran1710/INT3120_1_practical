fun main() {
    var timeSpentToday: Int = 300
    var timeSpentYesterday: Int = 250
    
    println(compare(timeSpentToday, timeSpentYesterday))
    
    timeSpentYesterday = 300
    
    println(compare(timeSpentToday, timeSpentYesterday))
    
    timeSpentToday = 200
    timeSpentYesterday = 220
    
    println(compare(timeSpentToday, timeSpentYesterday))
}

fun compare(num1: Int, num2: Int): Boolean {
    return num1 > num2
}