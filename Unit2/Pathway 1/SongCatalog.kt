fun main() {
    val song = Song("[Title]", "[Artist]", 2000, 0)
    song.printSongDescription()
    println(song.isPopular)
    
}

class Song(
    val title: String,
    val artist: String,
    val yearPublished: Int,
    val playCount: Int    
){
    val isPopular: Boolean
    	get() = playCount >= 1000
    
    fun printSongDescription() {
        println("$title, performed by $artist, was released in $yearPublished.")
    }
}