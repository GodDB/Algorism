import java.util.*

/** https://www.acmicpc.net/problem/2589 */
class Baekjoon_2589 {

    var n: Int = 0
    var m: Int = 0
    val cx = listOf<Int>(-1, 0, 1, 0)
    val cy = listOf<Int>(0, -1, 0, 1)

    var maxLevel: Int = Integer.MIN_VALUE
        set(value) {
            field = field.coerceAtLeast(value)
        }

    fun bfs(arr: List<List<String>>, point: Point8, visitList: MutableList<MutableList<Boolean>>) {
        val queue: Queue<Point8> = LinkedList()
        queue.add(point)

        while (queue.isNotEmpty()) {
            val value = queue.poll()

            for (i in 0 until 4) {
                val cx = value.x + cx[i]
                val cy = value.y + cy[i]

                if (cx < 0 || cx >= n || cy < 0 || cy >= m) continue
                if (arr[cx][cy] != "L") continue
                if (visitList[cx][cy]) continue

                visitList[cx][cy] = true
                val level = value.level + 1
                maxLevel = level
                queue.add(Point8(cx, cy, level))
            }
        }
    }
}

data class Point8(val x: Int, val y: Int, val level: Int)

fun main() {
    val scan = Scanner(System.`in`)
    val n = scan.nextInt()
    val m = scan.nextInt()
    val arr = MutableList<MutableList<String>>(n) { mutableListOf() }

    for (i in 0 until n) {
        val value = scan.next().split("").filter { it.isNotEmpty() && it != " " }.toMutableList()
        arr[i] = value
    }

    Baekjoon_2589().apply {
        this.m = m
        this.n = n
    }.run {
        arr.forEachIndexed { i, list ->
            list.forEachIndexed { j, value ->
                if (value == "L") {
                    val visitList = MutableList(n) { MutableList(m) { false } }
                    visitList[i][j] = true
                    bfs(arr, Point8(i, j, 0), visitList)
                }
            }
        }
        println(maxLevel)
    }
}