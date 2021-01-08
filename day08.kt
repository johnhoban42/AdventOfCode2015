import java.io.File

fun main(){

    val src = File("day08.txt").readLines()

    /* PART 1 */
    var literals = 0
    var memory = 0
    for(s in src){
        literals += s.length
        memory += s.length - 2 // starting and ending quotes
        var i = 1
        while(i < s.length-1){
            if(s[i] == '\\'){
                if(s[i+1] == '\"' || s[i+1] == '\\'){
                    memory -= 1
                    i += 2
                }else{ // hex
                    memory -= 3
                    i += 4
                }
            }else{
                i++
            }
        }
    }
    println(literals - memory)

    /* PART 2 */
    // Added chars in encoding = 2 quotes + existing occurrences of " and \
    val diff = src.map{str -> str.filter{c -> c == '\\' || c == '\"'}}
        .map{str -> 2 + str.length}
        .sum()
    println(diff)

}