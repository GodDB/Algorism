import java.util.*

/** https://www.acmicpc.net/problem/16197 */
class Baekjoon_16197 {

    private val cx = listOf(-1, 0, 1, 0)
    private val cy = listOf(0, -1, 0, 1)

    fun bfs(arr: List<List<String>>, n: Int, m: Int, queue: Queue<Pair<Coin, Coin>>): Int {
        while (queue.isNotEmpty()) {
            val coinZip = queue.poll()
            val coin1 = coinZip.first
            val coin2 = coinZip.second

            if (coin1.level >= 10 || coin2.level >= 10) {
                return -1
            }

            for (i in 0 until 4) {
                var newX1 = coin1.x + cx[i]
                var newY1 = coin1.y + cy[i]

                var newX2 = coin2.x + cx[i]
                var newY2 = coin2.y + cy[i]

                val isCoin1Out = (newX1 < 0 || newX1 >= n || newY1 < 0 || newY1 >= m)
                val isCoin2Out = (newX2 < 0 || newX2 >= n || newY2 < 0 || newY2 >= m)
                if (isCoin1Out && isCoin2Out) continue
                if (isCoin1Out || isCoin2Out) {
                    return coin2.level + 1
                }

                if (arr[newX1][newY1] == "#") {
                    newX1 = coin1.x
                    newY1 = coin1.y
                }

                if (arr[newX2][newY2] == "#") {
                    newX2 = coin2.x
                    newY2 = coin2.y
                }

                queue.offer(coin1.copy(x = newX1, y = newY1, level = coin1.level + 1) to coin2.copy(x = newX2, y = newY2, level = coin2.level + 1))
            }
        }
        return -1
    }
}

data class Coin(val pk: Int, val x: Int, val y: Int, val level: Int)

fun main() {
    val scan = Scanner(System.`in`)
    val n = scan.nextInt()
    val m = scan.nextInt()
    val arr = MutableList(n) { MutableList(m) { " " } }
    val queue: Queue<Pair<Coin, Coin>> = LinkedList()

    var pk = 0
    val targetList = mutableListOf<Coin>()
    for (i in 0 until n) {
        val valueArr = scan.next().split("").filter { it != "" && it != " " }
        for (j in 0 until m) {
            arr[i][j] = valueArr[j]

            if (valueArr[j] == "o") targetList.add(Coin(pk++, i, j, 0))
        }
    }
    queue.offer(targetList[0] to targetList[1])

    Baekjoon_16197().run {
        println(bfs(arr, n, m, queue))
    }
}