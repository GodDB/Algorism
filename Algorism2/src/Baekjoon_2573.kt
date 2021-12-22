import java.util.*
import kotlin.math.max

/** https://www.acmicpc.net/problem/2573 */
class Baekjoon_2573 {

    private val cx = listOf<Int>(-1, 0, 1, 0)
    private val cy = listOf<Int>(0, -1, 0, 1)

    var n = 0
    var m = 0

    fun bfs(arr: MutableList<MutableList<Int>>) {
        val queue: Queue<Point30> = LinkedList()
        val visitList = MutableList(n) { MutableList(m) { false } }

        for (i in 0 until n) {
            for (j in 0 until m) {
                if (arr[i][j] != 0) continue
                if (visitList[i][j]) continue

                queue.offer(Point30(i, j))
                visitList[i][j] = true
            }
        }

        while (queue.isNotEmpty()) {
            val point = queue.poll()

            for (k in 0 until 4) {
                val newX = point.x + cx[k]
                val newY = point.y + cy[k]

                if (newX < 0 || newX >= n || newY < 0 || newY >= m) continue
                if (visitList[newX][newY]) continue
                if (arr[newX][newY] == 1) {
                    arr[newX][newY] = 0
                    visitList[newX][newY] = true
                } else if (arr[newX][newY] != 0) {
                    arr[newX][newY] = max(arr[newX][newY] - 1, 0)
                    if (arr[newX][newY] == 0) visitList[newX][newY] = true
                } else {
                    queue.offer(Point30(newX, newY))
                    visitList[newX][newY] = true
                }
            }
        }

    }

    fun checkEmpty(arr: List<List<Int>>): Boolean {
        for (i in 0 until n) {
            for (j in 0 until m) {
                if (arr[i][j] != 0) return false
            }
        }
        return true
    }

    fun checkSplit(arr: List<List<Int>>): Int {
        var count = 0
        val visitList = MutableList(n) { MutableList(m) { false } }
        val queue: Queue<Point30> = LinkedList()
        for (i in 0 until n) {
            for (j in 0 until m) {
                if (count >= 2) return count
                if (arr[i][j] == 0) continue
                if (visitList[i][j]) continue
                queue.offer(Point30(i, j))
                visitList[i][j] = true
                count++
                while (queue.isNotEmpty()) {
                    val point = queue.poll()

                    for (z in 0 until 4) {
                        val newX = point.x + cx[z]
                        val newY = point.y + cy[z]

                        if (arr[newX][newY] == 0) continue
                        if (newX < 0 || newX >= n || newY < 0 || newY >= m) continue
                        if (visitList[newX][newY]) continue

                        visitList[newX][newY] = true
                        queue.offer(Point30(newX, newY))
                    }
                }
            }
        }
        return count
    }
}

fun main() {
    val scan = Scanner(System.`in`)
    val n = scan.nextInt()
    val m = scan.nextInt()
    val arr = MutableList(n) { MutableList(m) { 0 } }

    repeat(n) { i ->
        repeat(m) { j ->
            arr[i][j] = scan.nextInt()
        }
    }

    Baekjoon_2573().apply {
        this.n = n
        this.m = m
    }.run {
        var result = 0

        while (true) {
            if (checkEmpty(arr)) {
                return println(0)
            } else if (checkSplit(arr) >= 2) {
                return println(result)
            } else {
                result += 1
                bfs(arr)
            }
        }
    }
}

data class Point30(val x: Int, val y: Int)