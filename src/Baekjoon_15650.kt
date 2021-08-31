import java.util.*

/** https://www.acmicpc.net/problem/15650 */

fun main() {

    val scan = Scanner(System.`in`)
    val n = scan.nextInt()
    val m = scan.nextInt()

    val arr = MutableList(m) { 0 }

    backTracking(0, 0, n, m, arr)
}

private fun backTracking(index: Int, prevValue:Int, n: Int, m: Int, arr: MutableList<Int>) {

    if (index == m) {
        arr.forEach {
            print("$it ")
        }
        println()
        return
    }

    for (i in prevValue until n) {
        arr[index] = i + 1
        backTracking(index + 1, arr[index], n, m, arr)
    }
}