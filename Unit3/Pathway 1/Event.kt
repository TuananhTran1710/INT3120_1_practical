data class Event (
    val title: String,
    val description: String? = null,
    val daypart: Daypart,
    val durationInMinutes: Int,
)

val Event.durationOfEvent: String 
	get () = if (this.durationInMinutes < 60) "short" else "long"
    
enum class Daypart {
    Morning, 
    Afternoon, 
    Evening
}

fun pluralOf(num: Int): String {
    return if (num != 1) "s" else ""
}

fun main() {
    val events = mutableListOf<Event>(
    	Event(title = "Wake up", description = "Time to get up", daypart = Daypart.Morning, durationInMinutes = 0),
        Event(title = "Eat breakfast", daypart = Daypart.Morning, durationInMinutes = 15),
    	Event(title = "Learn about Kotlin", daypart = Daypart.Afternoon, durationInMinutes = 30),
    	Event(title = "Practice Compose", daypart = Daypart.Afternoon, durationInMinutes = 60),
    	Event(title = "Watch latest DevBytes video", daypart = Daypart.Afternoon, durationInMinutes = 10),
    	Event(title = "Check out latest Android Jetpack library", daypart = Daypart.Evening, durationInMinutes = 45)
    )
    println("All events: ")
    events.forEach {
        println(it)
    }
    println()
    val shortEventList = events.filter { it.durationInMinutes < 60 }
    val shortEventCountMessage = buildString {
        append("You have ${shortEventList.size} short event")
        append(pluralOf(shortEventList.size))
        append(".")
    }
    println(shortEventCountMessage)
    println()
    val eventsGroupedByDaypart = events.groupBy { it.daypart }
    eventsGroupedByDaypart.forEach { (daypart, events) ->
        println(buildString {
            append("$daypart: ${events.size} event")
            append(pluralOf(events.size))
            append(".")
        })
    }
    println()
    println("Last event of the day: ${events.last().title}")
    println()
    println("Duration of first event of the day: ${events[0].durationOfEvent}")
}

