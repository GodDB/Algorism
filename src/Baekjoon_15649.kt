import java.util.*

/** https://www.acmicpc.net/problem/15649 */
fun main() {
    val scan = Scanner(System.`in`)
    val n = scan.nextInt()
    val m = scan.nextInt()

    val arr = MutableList(m) { 0 }
    val check = MutableList(n) { false }
    backTracking(0, m, n, arr, check)

}

private fun backTracking(index: Int, m: Int, n: Int, arr: MutableList<Int>, check: MutableList<Boolean>) {
    if (index == m) {
        for (i in 0 until index) {
            print("${arr[i]} ")
        }
        println()
        return
    }

    for (i in 0 until n) {
        if (check[i]) continue

        check[i] = true
        arr[index] = i + 1
        backTracking(index + 1, m, n, arr, check)
        check[i] = false
    }
}