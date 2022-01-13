import java.util.*
import kotlin.math.max

class Baekjoon_1654 {

    var n: Int = 0 // 랜선 갯수
    var m: Int = 0 // 자른 랜선 갯수

    fun binarySearch(list: List<Int>, _start: Long, _end: Long): Long {

        var start = _start
        var end = _end
        var mid: Long = 0

        var answer : Long = 0

        while (start <= end) {
            mid = (start + end) / 2L

            val result = list.sumBy { split(it, mid).toInt() }

            //자른 랜선의 갯수가 m보다 작다면 단위를 줄인다.
            if (result < m) {
                end = mid - 1
            } else {
                // 자른 랜선의 갯수가 m보다 높거나 같다면 단위를 높인다.
                answer = max(answer, mid)
                start = mid + 1
            }
        }

        return answer
    }

    private fun split(lan: Int, targetValue: Long): Long {
        return lan / targetValue
    }
}

fun main() {

    val scan = Scanner(System.`in`)

    val n = scan.nextInt() // 랜선 갯수
    val m = scan.nextInt() // 자른 랜선 갯수
    val arr = mutableListOf<Int>()

    repeat(n) {
        arr.add(scan.nextInt())
    }


    Baekjoon_1654().apply {
        this.n = n
        this.m = m
    }.run {
        println(binarySearch(arr, 0, Int.MAX_VALUE.toLong()))
    }
}