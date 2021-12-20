import java.util.*

class Baekjoon_2573 {

    private val cx = listOf(-1, 0, 1, 0)
    private val cy = listOf(0, -1, 0, 1)

    var n = 0
    var m = 0

    fun passAYear(arr: MutableList<MutableList<Int>>, x: Int, y: Int) {
        if (arr[x][y] == 0) return

        for (i in 0 until 4) {
            val newX = cx[i] + x
            val newY = cy[i] + y

            if (newX < 0 || newX >= n || newY < 0 || newY >= m) continue
            if (arr[newX][newY] == 0 && arr[x][y] > 0) {
                arr[x][y] -= 1
            }
        }
    }

    fun checkTwoSplit(arr: MutableList<MutableList<Int>>): Boolean {
        val visitList = MutableList(n) { MutableList(m) { false } }
        var level = 0

        arr.print("before")
        arr.forEachIndexed { i, list ->
            list.forEachIndexed { j, value ->
                if(level >= 2) return true
                if (!visitList[i][j] && arr[i][j] != 0) {
                    visitList[i][j] = true
                    arr[i][j] = ++level
                    dfsImpl(arr, visitList, i, j, level)
                }
            }
        }
        arr.print("after")
        return false
    }

    private fun dfsImpl(
        arr: MutableList<MutableList<Int>>,
        visitList: MutableList<MutableList<Boolean>>,
        x: Int,
        y: Int,
        level: Int
    ) {
        for (i in 0 until 4) {
            val newX = cx[i] + x
            val newY = cy[i] + y

            if (newX < 0 || newX >= n || newY < 0 || newY >= m) continue
            if(arr[newX][newY] == 0) continue
            if (visitList[newX][newY]) continue
            arr[newX][newY] = level
            visitList[newX][newY] = true
            dfsImpl(arr, visitList, newX, newY, level)
        }
    }

    fun MutableList<MutableList<Int>>.deepCopy(): MutableList<MutableList<Int>> {
        val arr = mutableListOf<MutableList<Int>>()

        this.forEach {
            arr.add(it.toMutableList())
        }
        return arr
    }
}

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

    Baekjoon_2573().apply {
        this.n = n
        this.m = m
    }.run {
        for (z in 0 until 40) {
            if(checkTwoSplit(arr.deepCopy())) {
                println(z)
                return
            }
            for (i in n/2 until n) {
                for (j in m/2 until m) {
                    passAYear(arr, i, j)
                }
            }

            for(i in n/2 downTo 0) {
                for(j in m/2 downTo 0) {
                    passAYear(arr, i, j)
                }
            }
        }
        println(0)
    }
}
