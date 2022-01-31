import java.util.*

/** https://www.acmicpc.net/problem/1715 */

fun main() {
    val scan = Scanner(System.`in`)
    val n = scan.nextInt()

    val priorQueue : PriorityQueue<Int> = PriorityQueue { o1, o2 -> o1 - o2}

    repeat(n) {
        priorQueue.offer(scan.nextInt())
    }

    if(n == 1) return println(0)

    var answer = 0
    while (priorQueue.isNotEmpty()) {
        if( priorQueue.size == 1) break
        val priorQueueValue1 = priorQueue.poll()
        val priorQueueValue2 = priorQueue.poll()

        answer += priorQueueValue1 + priorQueueValue2
        priorQueue.offer(priorQueueValue1 + priorQueueValue2)
    }

    println(answer)
}