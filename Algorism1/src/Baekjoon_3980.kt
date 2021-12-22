import java.util.*

/** https://www.acmicpc.net/problem/3980 */

private var totalSum: Int = 0

fun main() {
    val scan = Scanner(System.`in`)
    val n = scan.nextInt()
    scan.nextLine()

    for (i in 0 until n) {
        val arr = MutableList<MutableList<Int>>(11) { mutableListOf() }
        val checkList = MutableList(11) { false }
        for (j in 0 until 11) {
            val values = scan.nextLine()
            values.split(" ")
                .asSequence()
                .filter { it != "" }
                .map { it.toInt() }
                .iterator()
                .forEach { arr[j].add(it) }
        }
        backTracking(arr, checkList, 0, 0)
        println("$totalSum")
        totalSum = 0
    }
}

private fun setTotalSumToMax(value: Int) {
    totalSum = totalSum.coerceAtLeast(value)
}

private fun backTracking(arr: List<List<Int>>, check: MutableList<Boolean>, i: Int, sum: Int) {
    if (i == 11) {
        setTotalSumToMax(sum)
        return
    }

    for (k in 0 until 11) {
        if (arr[i][k] == 0) continue
        if (check[k]) continue
        check[k] = true
        backTracking(arr, check, i + 1, sum + arr[i][k])
        check[k] = false
    }
}
