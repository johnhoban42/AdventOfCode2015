import java.math.BigInteger
import java.security.MessageDigest

fun main(){
    val src = "bgvyzdsv"
    val md = MessageDigest.getInstance("MD5")

    /* PART 1 */
    // Try different hash inputs until we generate hashes starting with 5 and 6 zeros
    var suffix = 0
    var done1 = false; var done2 = false
    while(!done1 || !done2) {
        val input = src + suffix
        val hash = BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
        // PART 1
        if(!done1 && hash.startsWith("00000")){
            println("PART 1: $suffix")
            done1 = true
        }
        // PART 2
        if(hash.startsWith("000000")){
            println("PART 2: $suffix")
            done2 = true
        }
        suffix++
    }

}
