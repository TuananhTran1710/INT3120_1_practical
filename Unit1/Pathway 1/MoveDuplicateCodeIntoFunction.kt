fun main() {
    printWeatherInfo(city = "Ankara", lowTemp = 27, highTemp = 31, rainChance = 82)
	printWeatherInfo(city = "Tokyo", lowTemp = 32, highTemp = 36, rainChance = 10)
    printWeatherInfo(city = "Cape Town", lowTemp = 59, highTemp = 64, rainChance = 2)
    printWeatherInfo(city = "Guatemala City", lowTemp = 50, highTemp = 55, rainChance = 7)
}

fun printWeatherInfo(city: String, lowTemp: Int, highTemp: Int, rainChance: Int) {
    println("City: $city")
    println("Low temperature: $lowTemp, High temperature: $highTemp")
    println("Chance of rain: $rainChance%")
    println()
}