import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

class Baekjoon_3020 {

    // targetValue가 oddList보다 크면 부딪힌다. (홀수)
    fun binarySearchOdd(oddList: List<Int>, targetValue: Int, _start: Int, _end: Int): Int {
        var start = _start
        var end = _end

        while (start <= end) {
            val mid = (start + end) / 2
            val midValue = oddList[mid]
            if (targetValue > midValue) {
                start = mid + 1
            } else {
                end = mid - 1
            }
        }

        return start
    }

    // targetValue가 evenList 보다 작으면 부딪힌다. (짝수)
    fun binarySearchEven(evenList: List<Int>, targetValue: Int, _start: Int, _end: Int): Int {
        var start = _start
        var end = _end

        while (start <= end) {
            val mid = (start + end) / 2
            val midValue = evenList[mid]

            if (targetValue <= midValue) {
                end = mid - 1
            } else {
                start = mid + 1
            }
        }
        return evenList.size - (end + 1)
    }
}

fun main() {
    val bf = BufferedReader(InputStreamReader(System.`in`))
    val nhList = bf.readLine().split(" ").filter { it != "" && it != " " }.map { it.toInt() }.toList()
    val n = nhList[0]
    val h = nhList[1]

    val oddList = mutableListOf<Int>()
    val evenList = mutableListOf<Int>()

    repeat(n) { index ->
        val input = bf.readLine().toInt()
        if (index % 2 == 0) evenList.add(input) else oddList.add(h - input)
    }

    oddList.sort()
    evenList.sort()

    var result = Int.MAX_VALUE
    var count = 1

    Baekjoon_3020().run {
        repeat(h + 1) { index ->
            if (index != 0) {
                val value = binarySearchOdd(oddList, index, 0, oddList.size - 1) + binarySearchEven(evenList, index, 0, evenList.size - 1)

                if (value < result) {
                    result = value
                    count = 1
                } else if (value == result) {
                    count++
                }
            }
        }
        println("$result $count")
    }
}