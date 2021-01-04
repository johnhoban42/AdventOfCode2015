import java.io.File

fun main(){

    val src = File("day01.txt").readLines()[0]

    var floor = 0
    var basement = 0
    for(i in src.indices){
        if(src[i] == ')') floor-- else floor++
        if(basement == 0 && floor < 0){
            basement = i + 1
        }
    }
    println(floor)      // PART 1
    println(basement)   // PART 2

}
