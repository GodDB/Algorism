import java.util.*

/** https://www.acmicpc.net/problem/16236 */
class Baekjoon_16236 {

    private val cx = listOf(-1, 0, 1, 0)
    private val cy = listOf(0, -1, 0, 1)

    var n: Int = 0

    fun bfs(arr: MutableList<MutableList<Int>>, queue: Queue<Shark>): Int {
        var time = 0
        var sharkSize = 2
        var count = 0

        val feed = PriorityQueue<Fish> { o1, o2 ->
            if (o1.distance == o2.distance) {
                if (o1.x == o2.x) o1.y - o2.y
                else o1.x - o2.x
            } else {
                o1.distance - o2.distance
            }
        }

        while (true) {
            val visitList = MutableList(n) { MutableList(n) { false } }

            while (queue.isNotEmpty()){
                val shark = queue.poll()
                visitList[shark.x][shark.y] = true

                for( i in 0 until 4) {
                    val newX = shark.x + cx[i]
                    val newY = shark.y + cy[i]

                    if(newX < 0 || newX >= n || newY < 0 || newY >= n) continue
                    if(visitList[newX][newY]) continue
                    if(arr[newX][newY] > sharkSize) continue

                    if(arr[newX][newY] == 0 || arr[newX][newY] == sharkSize) {
                        queue.offer(shark.move(newX, newY))
                        visitList[newX][newY] = true
                    } else {
                        shark.move(newX, newY).also {
                            queue.offer(it)
                            feed.offer(Fish(it.x, it.y, it.distance))
                            visitList[it.x][it.y] = true
                        }
                    }
                }
            }
            if(feed.isNotEmpty()) {
                val fish = feed.poll()

                if(++count == sharkSize) {
                    sharkSize++
                    count = 0
                }
                arr[fish.x][fish.y] = 0
                time += fish.distance
                queue.offer(Shark(fish.x, fish.y, 0))
                feed.clear()
            } else {
                break
            }
        }
        return time
    }
}

fun main() {
    val scan = Scanner(System.`in`)
    val n = scan.nextInt()
    val arr = MutableList(n) { MutableList(n) { 0 } }
    val queue = LinkedList<Shark>()
    for (i in 0 until n) {
        for (j in 0 until n) {
            arr[i][j] = scan.nextInt()

            if (arr[i][j] == 9) {
                queue.offer(Shark(i, j, 0))
                arr[i][j] = 0
            }
        }
    }

    Baekjoon_16236().apply {
        this.n = n
    }.run {
        println(bfs(arr, queue))
    }

}

data class Shark(val x: Int, val y: Int, val distance: Int) {

    fun move(newX: Int, newY: Int): Shark {
        return Shark(newX, newY, distance + 1)
    }
}

data class Fish(val x: Int, val y: Int, val distance: Int)