import java.util.*

/** https://www.acmicpc.net/problem/14502 */

class Baekjoon_14502 {

    private val cx = intArrayOf(0, 1, 0, -1)
    private val cy = intArrayOf(-1, 0, 1, 0)

    private val MAX_WALL_COUNT = 3

    var column: Int = 0
    var row: Int = 0

    var maxSum: Int = 0
        set(value) {
            field = field.coerceAtLeast(value)
        }


    private fun bfs(newArr: MutableList<MutableList<Int>>) {
        val visitNewList = MutableList(column) { MutableList(row) { false } }
        val queue: Queue<Point5> = LinkedList()

        for (i in 0 until column) {
            for (j in 0 until row) {
                if (newArr[i][j] == 2) {
                    queue.add(Point5(i, j))
                    visitNewList[i][j] = true
                }
            }
        }

        while (queue.isNotEmpty()) {
            val point = queue.poll()
            for (i in 0 until 4) {
                val newX = point.x + cx[i]
                val newY = point.y + cy[i]

                if (newX < 0 || newX >= column || newY < 0 || newY >= row) continue
                if (visitNewList[newX][newY]) continue
                if (newArr[newX][newY] != 0) continue

                queue.add(Point5(newX, newY))
                newArr[newX][newY] = 2
                visitNewList[newX][newY] = true
            }
        }
        searchAndPrint(newArr)
    }

    private fun searchAndPrint(newArr: List<List<Int>>) {
        var sum = 0
        newArr.forEach {
            it.forEach { value ->
                if (value == 0) sum++
            }
        }
        maxSum = sum
    }

    fun makeWall(
        wallCnt: Int,
        arr: MutableList<MutableList<Int>>
    ) {
        if (wallCnt == MAX_WALL_COUNT) {
            bfs(arr.deepCopy())
            return
        }

        for (i in 0 until column) {
            for (j in 0 until row) {
                if (arr[i][j] == 0) {
                    arr[i][j] = 1
                    makeWall(wallCnt+1, arr)
                    arr[i][j] = 0
                }
            }
        }
    }
}

data class Point5(val x: Int, val y: Int)

fun main() {
    val scan = Scanner(System.`in`)
    val column = scan.nextInt()
    val row = scan.nextInt()

    val arr = MutableList(column) { MutableList(row) { 0 } }

    for (i in 0 until column) {
        for (j in 0 until row) {
            arr[i][j] = scan.nextInt()
        }
    }
    val baekjoon: Baekjoon_14502 = Baekjoon_14502().apply {
        this.column = column
        this.row = row
    }.also {
        it.makeWall( 0, arr.deepCopy())
    }

    println(baekjoon.maxSum)
}

fun <T> MutableList<MutableList<T>>.deepCopy(): MutableList<MutableList<T>> {
    val arr = mutableListOf<MutableList<T>>()

    this.forEach {
        arr.add(it.toMutableList())
    }
    return arr
}