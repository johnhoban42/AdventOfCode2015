import java.io.File

fun main(){

    val src = File("day05.txt").readLines()

    /* PART 1 */
    val vowels = Regex("[aeiou].*[aeiou].*[aeiou]")
    val double = Regex("(.)\\1")
    val naughty = Regex("(ab|cd|pq|xy)")
    var nice = src.asSequence()
        .filter{s -> s.contains(vowels) && s.contains(double) && !s.contains(naughty)}
        .count()
    println(nice)

    /* PART 2 */
    val pair = Regex("(.{2}).*\\1")
    val repeat = Regex("(.).\\1")
    nice = src.asSequence()
        .filter{s -> s.contains(pair) && s.contains(repeat)}
        .count()
    println(nice)

}
