import java.util.*
import kotlin.math.max

fun main() {
    val scan = Scanner(System.`in`)
    val n = scan.nextInt()
    val m = scan.nextInt()

    val arr = MutableList(n) { 0 }
    backTracking(0, n, arr)

}

private fun backTracking(index: Int, maxIndex: Int, arr: MutableList<Int>) {
    if (index == maxIndex) {
        for( i in 0 until index) {
            print("${arr[i]} ")
        }
        println()
    }

    for( i in index until maxIndex) {
        arr[index] = i
        backTracking(index + 1, maxIndex, arr)
    }
}