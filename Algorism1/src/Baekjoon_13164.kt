import java.util.*

class Baekjoon_13164 {
}

fun main() {
    val scan = Scanner(System.`in`)

    val n = scan.nextInt()
    val m = scan.nextInt()
    val arr = mutableListOf<Int>()

    for (i in 0 until n) {
        arr.add(scan.nextInt())
    }

    val diff = mutableListOf<Int>()

    for (i in 0 until n - 1) {
        diff.add(arr[i + 1] - arr[i])
    }
    diff.sort()

    if (n == m) {
        println(0)
    } else {
        val sum = diff.filterIndexed { index, i -> index < n - m }.sum()
        println(sum)
    }
}