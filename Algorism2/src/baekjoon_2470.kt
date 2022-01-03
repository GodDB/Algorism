import java.util.*
import kotlin.math.abs
import java.io.BufferedReader
import java.io.InputStreamReader

/** https://www.acmicpc.net/problem/2470 */
class baekjoon_2470 {

    var targetValue: Int = Int.MAX_VALUE
    lateinit var keySet: Pair<Int, Int>

    fun binarySearch(arr: List<Int>, targetValue: Int, start: Int, end: Int) {
        if (start > end) return

        val mid = (start + end) / 2
        val midValue = arr[mid]

        if (midValue == targetValue) return

        val compareResult = compareToTargetValue(targetValue + midValue)

        if (compareResult == 0) {
            this.targetValue = targetValue + midValue
            this.keySet = targetValue to midValue
            binarySearch(arr, targetValue, start, mid - 1)
        } else if (compareResult == -1) {
            this.targetValue = targetValue + midValue
            this.keySet = targetValue to midValue
            binarySearch(arr, targetValue, mid + 1, end)
        } else if (compareResult == 1) {
            binarySearch(arr, targetValue, start, mid - 1)
        } else {
            binarySearch(arr, targetValue, mid + 1, end)
        }
    }


    // 크거나 같은데 마이너스다 2
    // 크거나 같은데 플러스다 1
    // 작은데 플러스다 0
    // 작은데 마이너스다 -1
    private fun compareToTargetValue(value: Int): Int {
        return if (abs(value) < abs(targetValue)) {
            if (value > 0) 0 else -1
        } else {
            if (value > 0) 1 else 2
        }
    }
}


fun main() {
    val bf = BufferedReader(InputStreamReader(System.`in`))
    val n = bf.readLine().toInt()
    val arr = bf.readLine().split(" ").filter { it != "" && it != " " }.map { it.toInt() }.toMutableList()

    arr.sort()

    baekjoon_2470().run {
        repeat(n) { index ->
            val targetValue = arr[index]
            binarySearch(arr, targetValue, 0, n - 1)
        }
        if (keySet.first > keySet.second) println("${keySet.second} ${keySet.first}")
        else println("${keySet.first} ${keySet.second}")
    }

}