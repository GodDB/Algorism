import java.util.*

/** https://www.acmicpc.net/problem/16197 */

private val cx2 = intArrayOf(0, 1, 0, -1)
private val cy2 = intArrayOf(-1, 0, 1, 0)

data class Position(val x: Int, val y: Int)

private var result5: Int = Int.MAX_VALUE
    set(value) {
        //최솟값
        field = field.coerceAtMost(value)
    }

fun main() {
    val scan = Scanner(System.`in`)
    val n = scan.nextInt()
    val m = scan.nextInt()

    val arr = MutableList(n) { MutableList(m) { "." } }
    var pos1: Position? = null
    var pos2: Position? = null

    for (i in 0 until n) {
        val input = scan.next().split("").filter { it != "" }
        input.forEachIndexed { index, s ->
            if (s == "o") {
                if (pos1 == null) pos1 = Position(i, index)
                else pos2 = Position(i, index)
            }
            arr[i][index] = s
        }
    }

    bfs(arr, pos1!!, pos2!!, n, m)
    /* backTracking(1, arr, pos1!!, pos2!!, n, m)*/
    /* if (result5 == Int.MAX_VALUE) println("-1")
     else println("${result5}")*/
}

private fun bfs(arr: List<List<String>>, _pos1: Position, _pos2: Position, n: Int, m: Int) {
    val queue: Queue<Position> = LinkedList<Position>().apply {
        add(_pos1)
        add(_pos2)
    }

    val pos1Visit: MutableList<MutableList<Boolean>> = MutableList(n) { MutableList(m) { false } }
    val pos2Visit = MutableList(n) { MutableList<Boolean>(m) { false } }

    var moveCnt = 0
    var isComplete: Boolean = false
    while (queue.isNotEmpty()) {
        if (isComplete) break
        if (moveCnt >= 10) {
            println("-1")
            isComplete = true
            break
        }
        val pos1 = queue.poll()
        val pos2 = queue.poll()

        for (i in 0 until 4) {
            val pos1_x = pos1.x + cx2[i]
            val pos1_y = pos1.y + cy2[i]
            val pos2_x = pos2.x + cx2[i]
            val pos2_y = pos2.y + cy2[i]

            //둘다 나가면 pass
            if (pos1_x < 0 && (pos2_x >= n || pos2_x < 0 || pos2_y < 0 || pos2_y >= m)) continue
            if (pos1_y < 0 && (pos2_x >= n || pos2_x < 0 || pos2_y < 0 || pos2_y >= m)) continue
            if (pos1_x >= n && (pos2_x >= n || pos2_x < 0 || pos2_y < 0 || pos2_y >= m)) continue
            if (pos1_y >= m && (pos2_x >= n || pos2_x < 0 || pos2_y < 0 || pos2_y >= m)) continue

            //둘중 하나 나가면 Good
            if (pos1_x < 0 || pos2_x < 0 || pos1_y < 0 || pos2_y < 0 || pos1_x >= n || pos2_x >= n || pos1_y >= m || pos2_y >= m) {
                println("$moveCnt")
                isComplete = true
                break
            }

            if (pos1Visit[pos1_x][pos1_y]) continue
            if (pos2Visit[pos2_x][pos2_y]) continue

            //#에 걸리면 동일한 값으로 backTracking
            if (arr[pos1_x][pos1_y] == "#" && arr[pos2_x][pos2_y] != "#") {
                queue.apply {
                    pos1Visit[pos1.x][pos1.y] = true
                    pos2Visit[pos2_x][pos2_y] = true
                    offer(pos1)
                    offer(pos2.copy(x = pos2_x, y = pos2_y))
                }
            } else if (arr[pos1_x][pos1_y] != "#" && arr[pos2_x][pos2_y] == "#") {
                queue.apply {
                    pos1Visit[pos1_x][pos1_y] = true
                    pos2Visit[pos2.x][pos2.y] = true
                    offer(pos1.copy(x = pos1_x, y = pos1_y))
                    offer(pos2)
                }
            } else if (arr[pos1_x][pos1_y] == "#" && arr[pos2_x][pos2_y] == "#") {
                queue.apply {
                    pos1Visit[pos1.x][pos1.y] = true
                    pos2Visit[pos2.x][pos2.y] = true
                    offer(pos1)
                    offer(pos2)
                }
            } else {
                queue.apply {
                    pos1Visit[pos1_x][pos1_y] = true
                    pos2Visit[pos2_x][pos2_y] = true
                    offer(pos1.copy(x = pos1_x, y = pos1_y))
                    offer(pos2.copy(x = pos2_x, y = pos2_y))
                }
            }
        }
        moveCnt++
    }

    if (!isComplete) println("-1")
}

private fun backTracking(
    moveCnt: Int,
    arr: List<List<String>>,
    pos1: Position,
    pos2: Position,
    n: Int,
    m: Int
) {
    //10번 이상 이동 했으면 종료
    if (moveCnt >= 10) {
        return
    }
    //이동 중에 최솟값보다 높으면 종료
    if (moveCnt >= result5) return

    for (i in 0 until 4) {
        val pos1_x = pos1.x + cx2[i]
        val pos1_y = pos1.y + cy2[i]
        val pos2_x = pos2.x + cx2[i]
        val pos2_y = pos2.y + cy2[i]

        //둘다 나가면 pass
        if (pos1_x < 0 && (pos2_x >= n || pos2_x < 0 || pos2_y < 0 || pos2_y >= m)) continue
        if (pos1_y < 0 && (pos2_x >= n || pos2_x < 0 || pos2_y < 0 || pos2_y >= m)) continue
        if (pos1_x >= n && (pos2_x >= n || pos2_x < 0 || pos2_y < 0 || pos2_y >= m)) continue
        if (pos1_y >= m && (pos2_x >= n || pos2_x < 0 || pos2_y < 0 || pos2_y >= m)) continue

        //둘중 하나 나가면 Good
        if (pos1_x < 0 || pos2_x < 0 || pos1_y < 0 || pos2_y < 0 || pos1_x >= n || pos2_x >= n || pos1_y >= m || pos2_y >= m) {
            result5 = moveCnt
            return
        }

        //#에 걸리면 동일한 값으로 backTracking
        if (arr[pos1_x][pos1_y] == "#" && arr[pos2_x][pos2_y] != "#") {
            backTracking(moveCnt + 1, arr, pos1, pos2.copy(x = pos2_x, y = pos2_y), n, m)
        } else if (arr[pos1_x][pos1_y] != "#" && arr[pos2_x][pos2_y] == "#") {
            backTracking(moveCnt + 1, arr, pos1.copy(x = pos1_x, y = pos1_y), pos2, n, m)
        } else if (arr[pos1_x][pos1_y] == "#" && arr[pos2_x][pos2_y] == "#") {
            backTracking(moveCnt + 1, arr, pos1, pos2, n, m)
        } else {
            backTracking(moveCnt + 1, arr, pos1.copy(x = pos1_x, y = pos1_y), pos2.copy(x = pos2_x, y = pos2_y), n, m)
        }
    }
}