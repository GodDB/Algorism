import java.util.*

/** https://www.acmicpc.net/problem/2636 */
class Baekjoon_2636 {

    private val cx = listOf(-1, 0, 1, 0)
    private val cy = listOf(0, -1, 0, 1)

    var n = 0
    var m = 0

    fun search(arr: MutableList<MutableList<Int>>): Pair<Int, Int> {
        var allMeltedTime = 0
        var cheeseCount = 0
        while (true) {
            cheeseCount = arr.getCheeseCount()
            searchInner(arr, MutableList(n) { MutableList(m) { false } })
            allMeltedTime++
            val isAllMelt = arr.confirmAllMelted()
            if (isAllMelt) break
        }
        return allMeltedTime to cheeseCount
    }

    private fun searchInner(arr: MutableList<MutableList<Int>>, visitList: MutableList<MutableList<Boolean>>) {
        val queue: Queue<Point14> = LinkedList()

        queue.offer(Point14(0, 0))

        while (queue.isNotEmpty()) {
            val point = queue.poll()

            for (i in 0 until 4) {
                val newX = point.x + cx[i]
                val newY = point.y + cy[i]

                if (newX < 0 || newX >= n || newY < 0 || newY >= m) continue
                if (visitList[newX][newY]) continue
                if (arr[newX][newY] == 1) {
                    arr[newX][newY] = 0
                    visitList[newX][newY] = true
                    continue
                }

                visitList[newX][newY] = true
                queue.offer(Point14(newX, newY))
            }
        }
    }

    fun MutableList<MutableList<Int>>.confirmAllMelted(): Boolean {
        this.forEach {
            it.forEach { value ->
                if (value != 0) return false
            }
        }
        return true
    }

    private fun MutableList<MutableList<Int>>.getCheeseCount(): Int {
        return this.sumBy { it.sumBy { value -> value } }
    }
}

data class Point14(val x: Int, val y: Int)

fun main() {
    val scan = Scanner(System.`in`)
    val n = scan.nextInt()
    val m = scan.nextInt()

    val arr = MutableList(n) { MutableList(m) { 0 } }
    for (i in 0 until n) {
        for (j in 0 until m) {
            arr[i][j] = scan.nextInt()
        }
    }
    val test = 0

    Baekjoon_2636().apply {
        this.n = n
        this.m = m
    }.run {
        val result = search(arr)
        println(result.first)
        println(result.second)
    }
}