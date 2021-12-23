import java.util.*

/** https://www.acmicpc.net/problem/1920 */
class Baekjoon_1920 {

    fun binarySearch(arr: List<Int>, targetValue: Int, start: Int, end: Int): Int {
        if (start > end) return 0

        val fivotIndex = (start + end) / 2
        val fivotValue = arr[fivotIndex]

        return if (targetValue == fivotValue) 1
        else if (targetValue > fivotValue) binarySearch(arr, targetValue, fivotIndex + 1, end)
        else binarySearch(arr, targetValue, start, fivotIndex - 1)
    }
}

fun main() {
    val scan = Scanner(System.`in`)
    val n = scan.nextInt()
    val arr = mutableListOf<Int>()

    repeat(n) { arr.add(scan.nextInt()) }

    val m = scan.nextInt()
    val targetList = mutableListOf<Int>()
    repeat(m) { targetList.add(scan.nextInt()) }

    arr.sort()

    Baekjoon_1920().run {
        targetList.forEach { targetValue ->
            println(binarySearch(arr, targetValue, 0, arr.size - 1))
        }
    }
}