import java.util.*

/** https://www.acmicpc.net/problem/1715 */

fun main() {
    val scan = Scanner(System.`in`)
    val n = scan.nextInt()
    val priorQueue: PriorityQueue<Int> = PriorityQueue()

    for (i in 0 until n) {
        priorQueue.add(scan.nextInt())
    }

    var curSum = 0

    while (priorQueue.isNotEmpty()) {
        val first = priorQueue.poll()
        val second = if (priorQueue.isNotEmpty()) priorQueue.poll() else {
            println("$curSum")
            break
        }
        curSum += first + second
        priorQueue.add(first + second)
    }
}