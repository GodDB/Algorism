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
    if (!check(sum)) return

    if (sum.length == n) {
        println(sum)
        exitProcess(0)
    }

    for (i in arr.indices) {
        backTracking(sum + arr[i].toString(), arr, n)
    }
}

private fun check(sum: String): Boolean {
    for (step in 1 until sum.length / 2 + 1) {
        for (i in 0 until step) {
            var prevValue = ""
            for (j in sum.indices step step) {
                if (sum.length < j + i + step) break
                val curValue = sum.substring(j + i, j + i + step)
                if (prevValue == curValue) return false
                else prevValue = curValue
            }
        }
    }
    return true
}