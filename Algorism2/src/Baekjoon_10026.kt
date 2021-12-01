import java.util.*

class Baekjoon_10026 {

    private val cx = listOf(-1, 0, 1, 0)
    private val cy = listOf(0, -1, 0, 1)

    var n = 0

    fun bfs(arr: List<List<String>>, isBlindividual: Boolean): Int {
        var result = 0
        val visitList = MutableList(n) { MutableList(n) { false } }
        arr.forEachIndexed { i, list ->
            list.forEachIndexed { j, value ->
                if (!visitList[i][j]) {
                    result++
                    bfsInner(arr, visitList, isBlindividual, value, i, j)
                }
            }
        }
        return result
    }

    private fun bfsInner(
        arr: List<List<String>>,
        visitList: MutableList<MutableList<Boolean>>,
        isBlindividual: Boolean,
        targetColor: String,
        x: Int,
        y: Int
    ) {
        val queue: Queue<Point> = LinkedList()
        queue.offer(Point(x, y))
        visitList[x][y] = true

        while (queue.isNotEmpty()) {
            val pollValue = queue.poll()
            for (i in 0 until 4) {
                val cx = pollValue.x + cx[i]
                val cy = pollValue.y + cy[i]

                if (cx < 0 || cx >= n || cy < 0 || cy >= n) continue
                if (visitList[cx][cy]) continue
                if (isBlindividual) {
                    if (targetColor == "B") {
                        if (targetColor != arr[cx][cy]) continue
                    } else {
                        if (arr[cx][cy] == "B") continue
                    }
                } else {
                    if (targetColor != arr[cx][cy]) continue
                }

                visitList[cx][cy] = true
                queue.offer(Point(cx, cy))
            }
        }
    }
}

data class Point(val x: Int, val y: Int)

fun main() {
    val scan = Scanner(System.`in`)

    val n = scan.nextInt()
    val arr: MutableList<MutableList<String>> = MutableList(n) { mutableListOf() }

    for (i in 0 until n) {
        val inputList = scan.next().split("").filter { it != "" && it != " " }
        arr[i].addAll(inputList)
    }

    Baekjoon_10026().apply {
        this.n = n
    }.run {
        print("${bfs(arr, false)} ")
        print(bfs(arr, true))
    }
}