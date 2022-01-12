import java.util.*

/** https://www.acmicpc.net/problem/3055 */
class Baekjoon_3055 {

    var n = 0
    var m = 0

    private val cx = listOf<Int>(0, 0, -1, 1) //위 아래 좌 우
    private val cy = listOf<Int>(-1, 1, 0, 0) //위 아래 좌 우

    /**
     * D - 비버 굴
     * S - 고슴도치
     * . - 빈곳 (이동 가능)
     * X - 돌 (이동 불가 - 물, 고슴도치)
     * * - 물
     * */
    fun bfs(list: MutableList<MutableList<String>>, queue: Queue<Location>, visitList: MutableList<MutableList<Boolean>>, bibberLocation: Location): String {

        while (queue.isNotEmpty()) {
            val location = queue.poll()

            for (i in 0 until 4) {
                val newX = location.x + cx[i]
                val newY = location.y + cy[i]

                if (newX < 0 || newX >= n || newY < 0 || newY >= m) continue
                if (visitList[newX][newY]) continue //재 방문 할 수 없다.
                if (location.type == "*" && list[newX][newY] == "D") continue // 물은 비버집에 범람할 수 없다.
                if (location.type == "*" && list[newX][newY] == "X") continue // 물은 돌에 접근 할 수 없다.
                if (location.type == "S" && list[newX][newY] == "*") continue // 고슴도치는 물을 건널 수 앖다.
                if (location.type == "S" && list[newX][newY] == "X") continue // 고슴도치는 돌에 접근 할 수 없다.

                if (bibberLocation.x == newX && bibberLocation.y == newY) { // 발견
                    return (location.level + 1).toString()
                }
                visitList[newX][newY] = true
                queue.offer(Location(newX, newY, location.level + 1, location.type))
            }
        }

        return "KAKTUS"
    }
}

fun main() {
    val scan = Scanner(System.`in`)
    val n = scan.nextInt()
    val m = scan.nextInt()

    val arr = MutableList(n) { mutableListOf<String>() }

    val waterLocation = mutableListOf<Location>()
    var hedgehogLocation: Location? = null
    var bibberLocation: Location? = null

    repeat(n) { i ->
        scan.next().split("").filter { it != "" && it != " " }.forEachIndexed { j, value ->
            if (value == "*") {
                waterLocation.add(Location(i, j, 0, value))
            } else if (value == "S") {
                hedgehogLocation = Location(i, j, 0, value)
            } else if (value == "D") {
                bibberLocation = Location(i, j, 0, value)
            }
            arr[i].add(value)
        }
    }

    Baekjoon_3055().apply {
        this.n = n
        this.m = m
    }.run {
        val queue: Queue<Location> = LinkedList()
        val visitList: MutableList<MutableList<Boolean>> = MutableList(n) { MutableList(m) { false } }

        waterLocation.forEach {
            queue.offer(it)
            visitList[it.x][it.y] = true
        }
        queue.offer(hedgehogLocation)
        visitList[hedgehogLocation!!.x][hedgehogLocation!!.y] = true

        println(bfs(arr, queue, visitList, bibberLocation!!))
    }
}

data class Location(val x: Int, val y: Int, val level: Int, val type: String)