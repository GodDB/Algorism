import java.util.*

/** https://www.acmicpc.net/problem/9663 */

private var resultSum: Int = 0

fun main() {
    val scan = Scanner(System.`in`)

    val n = scan.nextInt()
    val arr = MutableList(n) { -1 }

    backTracking1(0, n, arr)
    println("${resultSum}")
}

private fun backTracking1(x: Int, n: Int, arr: MutableList<Int>) {
    if (x == n) {
        if (!arr.contains(-1)) {
            resultSum++
        }
        return
    }

    for (value in 0 until n) {
        if (check(arr, x, value)) {
            arr[x] = value
            backTracking1(x + 1, n, arr)
        }
    }

}

private fun check(arr: MutableList<Int>, x: Int, value: Int): Boolean {
    for (i in 0 until x) {
        // 대각선 검증
        if (arr[i] + i == value + x) return false
        if (arr[i] - i == value - x) return false
        // 열 검증
        if (arr[i] == value) return false
    }
    return true
}