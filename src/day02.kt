import java.io.File
import java.lang.Math.min

fun main(){

    val src = File("inputs/day02.txt").readLines()
        .map{str -> str.split("x")}

    var area = 0 // PART 1
    var ribbon = 0 // PART 2
    for(d in src){
        val l = Integer.parseInt(d[0])
        val w = Integer.parseInt(d[1])
        val h = Integer.parseInt(d[2])
        val smallestSide = min(l*w, min(w*h, l*h))
        val smallestPerimeter = min(2*(l+w), min(2*(w+h), 2*(l+h)))
        area += 2*l*w + 2*w*h + 2*l*h + smallestSide
        ribbon += (smallestPerimeter + l*w*h)
    }
    println(area)
    println(ribbon)

}
