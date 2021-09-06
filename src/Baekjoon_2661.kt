import java.util.*
import kotlin.system.exitProcess

/** https://www.acmicpc.net/problem/2661 */

fun main() {
    val scan = Scanner(System.`in`)
    val n = scan.nextInt()

    val arr = IntArray(3) { it + 1 }

    backTracking("", arr, n)
}

private fun backTracking(sum: String, arr: IntArray, n: Int) {
    if(!check(sum, n)) return

    if(sum.length == n) {
        println(sum)
        exitProcess(0)
    }

    for (i in arr.indices) {
        backTracking(sum + arr[i].toString(), arr, n)
    }
}

private fun check(sum: String, n : Int): Boolean {
    if(n == 1) return true

    var prevValue = ""
    for (i in 0 until sum.length - 1) {
        if(sum[i] == sum[i+1]) return false
        if(i%2 != 0) continue
        if(prevValue == "${sum[i]}${sum[i+1]}") return false
        prevValue = "${sum[i]}${sum[i+1]}"
    }
    return true
}