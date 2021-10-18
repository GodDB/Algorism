import java.util.*

data class Point10(val x: Int, val y: Int, val level: Int)

class Baekjoon_17141 {

    private val cx = intArrayOf(0, 1, 0, -1)
    private val cy = intArrayOf(-1, 0, 1, 0)

    var n: Int = 0
    var m: Int = 0
    lateinit var virusAreaList: List<Point10>

    var result: Int = Int.MAX_VALUE
        set(value) {
            field = field.coerceAtMost(value)
        }
        get() = if (field == Int.MAX_VALUE) -1 else field


    fun backTracking(
        arr: MutableList<MutableList<Int>>,
        virusArea: MutableList<Point10>,
        index: Int,
        count: Int
    ) {
        if (count == m) {
            bfs(arr.deepCopy(), virusArea)
            return
        }

        for (i in index until virusAreaList.size) {
            val point = virusAreaList[i]
            arr[point.x][point.y] = 3
            virusArea.add(point)
            backTracking(arr, virusArea, i + 1, count + 1)
            arr[point.x][point.y] = 2
            virusArea.remove(point)
        }
    }

    private fun bfs(arr: MutableList<MutableList<Int>>, virusArea: List<Point10>) {
        val visitList = MutableList(n) { MutableList(n) { false } }
        val queue: Queue<Point10> = LinkedList()

        virusArea.forEach {
            queue.add(it)
            visitList[it.x][it.y] = true
            arr[it.x][it.y] = 1
        }

        var bfsResult = 0

        while (queue.isNotEmpty()) {
            val point = queue.poll()

            for (i in 0 until 4) {
                val cx = point.x + cx[i]
                val cy = point.y + cy[i]

                if (cx < 0 || cx >= n || cy < 0 || cy >= n) continue
                if (visitList[cx][cy]) continue
                if (arr[cx][cy] == 1) continue

                queue.add(Point10(cx, cy, point.level + 1))
                bfsResult = bfsResult.coerceAtLeast(point.level + 1)
                arr[cx][cy] = 1
                visitList[cx][cy] = true
            }
        }

        if (checkAllSearch(arr)) {
            result = bfsResult
        }
    }

    private fun checkAllSearch(arr: List<List<Int>>): Boolean {
        arr.forEach {
            it.forEach { value ->
                if (value != 1) return false
            }
        }
        return true
    }
}


fun main() {

    val scan = Scanner(System.`in`)

    val n = scan.nextInt()
    val m = scan.nextInt()

    val arr = MutableList(n) { MutableList(n) { 0 } }
    val virusAreaList = mutableListOf<Point10>()

    for (i in 0 until n) {
        for (j in 0 until n) {
            arr[i][j] = scan.nextInt()

            if (arr[i][j] == 2) {
                virusAreaList.add(Point10(i, j, 0))
            }
        }
    }

    Baekjoon_17141().apply {
        this.n = n
        this.m = m
        this.virusAreaList = virusAreaList
    }.run {
        backTracking(arr, mutableListOf(), 0, 0)
        println(result)
    }

}

fun MutableList<MutableList<Int>>.deepCopy(): MutableList<MutableList<Int>> {
    return this.map {
        it.toMutableList()
    }.toMutableList()
}