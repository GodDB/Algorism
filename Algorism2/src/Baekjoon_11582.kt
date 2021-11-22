import java.util.*
import kotlin.system.exitProcess

class Baekjoon_11582 {
    var n = 0
    var m = 0

    val tempArr by lazy { MutableList(n) { 0 } }

    fun divide(arr: MutableList<Int>, startIndex: Int, endIndex: Int) {
        if(startIndex >= endIndex) return

        val pivot = (endIndex + startIndex) / 2

        divide(arr, startIndex, pivot)
        divide(arr, pivot + 1, endIndex)
        conquar(arr, startIndex, endIndex)
    }

    private fun conquar(arr: MutableList<Int>, startIndex: Int, endIndex: Int) {
        if ((endIndex - startIndex) > (n / m)) return

        val pivotIndex = (startIndex + endIndex) / 2

        var i = startIndex
        var j = pivotIndex + 1
        var k = startIndex

        while (i <= pivotIndex && j <= endIndex) {
            if (arr[i] < arr[j]) {
                tempArr[k++] = arr[i++]
            } else {
                tempArr[k++] = arr[j++]
            }
        }

        while (i <= pivotIndex) {
            tempArr[k++] = arr[i++]
        }

        while (j <= endIndex) {
            tempArr[k++] = arr[j++]
        }

        for (z in startIndex..endIndex) {
            arr[z] = tempArr[z]
        }
    }
}

fun main() {
    val scan = Scanner(System.`in`)
    val n = scan.nextInt()
    val arr = mutableListOf<Int>()

    for (i in 0 until n) {
        arr.add(scan.nextInt())
    }

    val m = scan.nextInt()

    Baekjoon_11582().apply {
        this.n = n
        this.m = m
    }.run {
        divide(arr, 0, n - 1)
        println(arr.joinToString(" "))
    }

}