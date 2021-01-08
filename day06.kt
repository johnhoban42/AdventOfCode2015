import java.io.File

fun main(){

    val src = File("day06.txt").readLines()

    // Part 1: turn on = assign 1, turn off = assign 0, toggle = xor 1
    // Part 2: turn on = +1, turn off = -1, toggle = +2
    val grid1 = Array(1000) {IntArray(1000)}
    val grid2 = Array(1000) {IntArray(1000)}
    for(line in src){
        // Strip instructions from input and execute the proper instruction on the lights grid
        val cmd = line.split(Regex("(turn off |turn on |toggle |,| through )"))
        val x1 = Integer.parseInt(cmd[1])
        val y1 = Integer.parseInt(cmd[2])
        val x2 = Integer.parseInt(cmd[3])
        val y2 = Integer.parseInt(cmd[4])
        if(line.startsWith("turn off")) {
            for (x in x1..x2) {
                for (y in y1..y2) {
                    grid1[x][y] = 0
                    grid2[x][y] = (grid2[x][y] - 1).coerceAtLeast(0)
                }
            }
        }else if(line.startsWith("turn on")){
            for (x in x1..x2) {
                for (y in y1..y2) {
                    grid1[x][y] = 1
                    grid2[x][y]++
                }
            }
        }else{ // toggle
            for (x in x1..x2) {
                for (y in y1..y2) {
                    grid1[x][y] = grid1[x][y] xor 1
                    grid2[x][y] += 2
                }
            }
        }
    }

    // Count lights
    val count1 = grid1.map{arr -> arr.sum()}.sum()
    val count2 = grid2.map{arr -> arr.sum()}.sum()
    println(count1)
    println(count2)

}