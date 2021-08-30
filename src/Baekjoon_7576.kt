import java.util.*

/** https://www.acmicpc.net/problem/7576 */

val cx = listOf<Int>(-1, 0, 1, 0)
val cy = listOf<Int>(0, -1, 0, 1)

fun main() {

    val scan = Scanner(System.`in`)
    val n = scan.nextInt()
    val m = scan.nextInt()

    val arr: MutableList<MutableList<Int>> = MutableList(m) { MutableList(n) { 0 } }
    val visitList: MutableList<MutableList<Boolean>> = MutableList(m) { MutableList(n) { false } }
    val resultList: MutableList<MutableList<Int>> = MutableList(m) { MutableList(n) { -1 } }
    for (i in 0 until m) {
        for (j in 0 until n) {
            arr[i][j] = scan.nextInt()
        }
    }

    bfs1(arr, visitList, resultList, m, n)

    var max = Integer.MIN_VALUE
    for( i in 0 until m) {
        for(j in 0 until n) {
            if(arr[i][j] != -1 && resultList[i][j] == -1) return println("-1")

            val value = resultList[i][j]
            if(max < value) {
                max = value
            }
        }
    }

    println("$max")
}

private fun bfs1(
    arr: List<List<Int>>,
    visitList: MutableList<MutableList<Boolean>>,
    resultList: MutableList<MutableList<Int>>,
    n: Int,
    m: Int
) {
    val queue: Queue<Triple<Int, Int, Int>> = LinkedList()
    for (i in 0 until n) {
        for (j in 0 until m) {
            if (arr[i][j] == 1) {
                visitList[i][j] = true
                resultList[i][j] = 0
                queue.add(Triple(i, j, 0))
            }
        }
    }

    while (queue.isNotEmpty()) {
        val indexPair = queue.poll()
        val newI = indexPair.first
        val newJ = indexPair.second
        val depth = indexPair.third

        for (k in 0 until 4) {
            val newX = newI + cx[k]
            val newY = newJ + cy[k]

            if (newX < 0 || newX >= n || newY < 0 || newY >= m || visitList[newX][newY]) continue
            if(arr[newX][newY] == -1){
                resultList[newX][newY] = depth
                continue
            }
            visitList[newX][newY] = true
            resultList[newX][newY] = depth + 1
            queue.add(Triple(newX, newY, depth + 1))
        }
    }
}