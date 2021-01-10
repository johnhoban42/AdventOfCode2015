import java.io.File

fun main(){

    val src = File("inputs/day03.txt").readLines()[0]

    /* PART 1 */
    var houses = HashMap<List<Int>, Int>() // map of house coordinates and times visited
    var x = 0; var y = 0
    for(c in src){
        when(c){
            '^' -> y++
            'v' -> y--
            '<' -> x--
            '>' -> x++
        }
        val coordinates = listOf(x, y)
        val timesVisited = houses[coordinates]
        houses[coordinates] = if(timesVisited == null) 1 else timesVisited + 1
    }
    println(houses.size)

    /* PART 2 */
    houses = HashMap<List<Int>, Int>()
    var sx = 0; var sy = 0 // Santa
    var rx = 0; var ry = 0 // Robo-Santa
    houses[listOf(0, 0)] = 2 // each Santa delivers presents before movement starts
    for(i in src.indices step 2){
        when(src[i]){
            '^' -> sy++
            'v' -> sy--
            '<' -> sx--
            '>' -> sx++
        }
        when(src[i+1]){
            '^' -> ry++
            'v' -> ry--
            '<' -> rx--
            '>' -> rx++
        }
        val sCoor = listOf(sx, sy)
        var timesVisited = houses[sCoor]
        houses[sCoor] = if(timesVisited == null) 1 else timesVisited + 1
        val rCoor = listOf(rx, ry)
        timesVisited = houses[rCoor]
        houses[rCoor] = if(timesVisited == null) 1 else timesVisited + 1
    }
    println(houses.size)
}
