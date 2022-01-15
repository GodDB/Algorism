import java.util.*
import kotlin.math.min

class Baekjoon_1916 {
}

private var minCost = Int.MAX_VALUE

fun main() {
    val scan = Scanner(System.`in`)
    val cityN = readLine()?.toInt() ?: return
    val busNum = readLine()?.toInt() ?: return

    val arr: MutableList<MutableList<Destination>> = MutableList(cityN + 1) { mutableListOf() }

    repeat(busNum) { index ->
        arr[scan.nextInt()].add(Destination(scan.nextInt(), scan.nextInt()))
    }

    val start = Human(scan.nextInt(), 0)
    val end = scan.nextInt()

    dijkstra(cityN, arr, start, end)

    println(minCost)
}


private fun dijkstra(cityNum: Int, arr: List<List<Destination>>, start: Human, end: Int) {
    val queue: PriorityQueue<Human> = PriorityQueue<Human> { o1, o2 ->
        o1.location - o2.location
    }
    val distances: MutableList<Int> = MutableList(cityNum + 1) { Int.MAX_VALUE }

    queue.offer(start)

    while (queue.isNotEmpty()) {
        val human = queue.poll()

        if (human.level > distances[human.location]) continue

        arr[human.location].forEachIndexed { j, destination ->

            if (destination.to == end) {
                minCost = min(destination.distance + human.level, minCost)
            }

            if (distances[destination.to] > destination.distance + human.level) {
                distances[destination.to] = destination.distance + human.level
                queue.offer(Human(destination.to, destination.distance + human.level))
            }
        }
    }
}

data class Human(val location: Int, val level: Int)

data class Destination(val to: Int, val distance: Int)