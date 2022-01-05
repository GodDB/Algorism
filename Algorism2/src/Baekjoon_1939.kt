import java.util.*
import kotlin.math.max

/** https://www.acmicpc.net/problem/1939 */
class Baekjoon_1939 {

    var n: Int = 0
    var m: Int = 0
    var fromFactory: Int = 0
    var toFactory: Int = 0

    fun binarySearch(_start: Int, _end: Int, arr: List<List<Info>>): Int {
        val visitList = MutableList(n + 1) { false }
        val queue: Queue<Info> = LinkedList()

        var start = _start
        var end = _end
        while (start <= end) {
            val mid = (start + end) / 2

            if (bfs(mid, arr, queue.apply { clear() }, visitList.apply { fill(false) })) {
                start = mid + 1 // 더 큰 값을 찾기 위해 올린다.
            } else {
                end = mid - 1 // 더 작은 값을 찾기 위해 내린다
            }
        }
        return start - 1
    }

    private fun bfs(
        targetWeight: Int,
        arr: List<List<Info>>,
        queue: Queue<Info>,
        visitList: MutableList<Boolean>
    ): Boolean {
        queue.offer(Info(0, fromFactory, 0))
        visitList[fromFactory] = true

        while (queue.isNotEmpty()) {
            val fromInfo = queue.poll()

            // 여기 까지 올 수 있었던 것은 이 거리를 지나면서 타겟 weight를 넘긴 경우가 있었기 때문이다.
            if (fromInfo.to == toFactory) return true

            for (info in arr[fromInfo.to]) {
                if (visitList[info.to]) continue

                if (info.weight >= targetWeight) {
                    queue.offer(info)
                    visitList[info.to] = true
                }
            }
        }
        return false
    }
}

fun main() {

    val scan = Scanner(System.`in`)
    val n = scan.nextInt() //섬 갯수 - outer array count
    val m = scan.nextInt() //인풋 갯수

    val arr = MutableList(n + 1) { mutableListOf<Info>() }
    var maxWeight = Int.MIN_VALUE
    repeat(m) { i ->
        val from = scan.nextInt()
        val to = scan.nextInt()
        val weight = scan.nextInt()

        arr[from].add(Info(from, to, weight))
        arr[to].add(Info(to, from, weight))
        maxWeight = max(maxWeight, weight)
    }

    val fromFactory = scan.nextInt()
    val toFactory = scan.nextInt()

    Baekjoon_1939().apply {
        this.n = n
        this.m = m
        this.fromFactory = fromFactory
        this.toFactory = toFactory
    }.run {
        println(binarySearch(1, maxWeight, arr))
    }
}

data class Info(val from: Int, val to: Int, val weight: Int)

