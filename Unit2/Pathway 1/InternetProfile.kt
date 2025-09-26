fun main() {    
    val amanda = Person("Amanda", 33, "play tennis", null)
    val atiqah = Person("Atiqah", 28, "climb", amanda)
    
    amanda.showProfile()
    atiqah.showProfile()
}


class Person(val name: String, val age: Int, val hobby: String?, val referrer: Person?) {
    fun showProfile() {
       // Fill in code 
        println("Name: $name")
        println("Age: $age")
        if (hobby == null) {
            print("Doesn't have a hobby. ")
        } else {
            print("Likes to $hobby. ")
        }
        if (referrer == null) {
            println("Doesn't have a referrer.")
            println()
        } else {
            print("Has a referrer named ${referrer.name}, who ")
            if (referrer.hobby == null) {
                println("doesn't have a hobby.")
            } else {
                println("likes to ${referrer.hobby}.")
            }
        }
    }
}