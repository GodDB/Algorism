import java.util.*

class Baekjoon_1941 {

    private val cx = intArrayOf(0, 1, 0, -1)
    private val cy = intArrayOf(-1, 0, 1, 0)

    private val map = MutableList(5) { MutableList(5) { false } }
    var result = 0

    // 25C7 (25개중 7개 조합)
    fun dfs(arr: List<List<String>>, index: Int, count: Int, s: Int) {
        if (count == 7) {
            if (s >= 4) {
                val point = findTrue()
                if (check(point.x, point.y, map.deepCopy())) result++
            }
            return
        }

        for (i in index until 25) {
            map[i / 5][i % 5] = true
            dfs(arr = arr,
                index = i + 1,
                count = count + 1,
                s = arr[i / 5][i % 5].let { if (it == "S") s + 1 else s }
            )
            map[i / 5][i % 5] = false
        }
    }

    private fun findTrue(): Point20 {
        map.forEachIndexed { i, list ->
            list.forEachIndexed { j, value ->
                if (value) {
                    return Point20(i, j)
                }
            }
        }
        return Point20(0, 0) //이거 탈일은 없다... 문법상 오류
    }

    private fun check(x: Int, y: Int, list: MutableList<MutableList<Boolean>>): Boolean {
        val queue: Queue<Point20> = LinkedList()
        queue.add(Point20(x, y))

        //bfs 탐색을 통해 인접값들을 모두 false로 바꾼다.
        while (queue.isNotEmpty()) {
            val point = queue.poll()
            for (i in 0 until 4) {
                val cx = point.x + cx[i]
                val cy = point.y + cy[i]

                if (cx < 0 || cx >= 5 || cy < 0 || cy >= 5) continue
                if (!list[cx][cy]) continue

                list[cx][cy] = false
                queue.add(Point20(cx, cy))
            }
        }

        // 다 바뀌었는지 체크한다. 바뀌지 않았다면 인접하지 않았다.
        return list.all { it.all { !it } }
    }
}

data class Point20(val x: Int, val y: Int)

fun main() {
    val scan = Scanner(System.`in`)
    val arr = mutableListOf<List<String>>()

    for (i in 0 until 5) {
        val value = scan.next().split("").filter { it != "" && it != " " }
        arr.add(value)
    }

    Baekjoon_1941().run {
        dfs(arr, 0, 0, 0)
        println(result)
    }
}

fun <T> MutableList<MutableList<T>>.deepCopy(): MutableList<MutableList<T>> =
    this.map { it.toMutableList() }.toMutableList()
