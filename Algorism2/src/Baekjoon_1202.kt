import java.util.*

/** https://www.acmicpc.net/problem/1202 */

/**
 * 두개의 우선순위 큐를 사용한다. (jewelleryQueue (이하 a) : 가벼운 주얼리 순 , prevPriorityQueue (이하 b) : 비싼 주얼리 순)
 *
 * 가방을 가벼운 순으로 정렬하고 a를 순회하면서 주얼리가 가방보다 가볍다면 b에 담는다 (가벼운 주일리 중 비싼 주얼리순으로 정렬된다.)
 * 순회하다 무거운 주얼리를 발견하면 b에서 가장 비싼 주얼리를 가방에 담는다.
 *
 * 시간 복잡도 대충 O(K * logK(정렬) + 가방 순회 K * 큐 순회 (logN))
 */
fun main() {
    val scan = Scanner(System.`in`)
    val n = scan.nextInt() // 총 보석 갯수
    val k = scan.nextInt() // 총 가방 갯수
    val backList = mutableListOf<Bag>()

    //가벼운 순으로 정렬한다.
    val jewelleryQueue = PriorityQueue<Jewellery> { o1, o2 ->
        if (o1.weight == o2.weight) {
            o2.price - o1.price
        } else {
            o1.weight - o2.weight
        }
    }

    //비싼 순으로 정렬한다.
    val prevPriorityQueue = PriorityQueue<Jewellery> { o1, o2 ->
        o2.price - o1.price
    }

    repeat(n) {
        jewelleryQueue.offer(Jewellery(scan.nextInt(), scan.nextInt()))
    }

    repeat(k) {
        backList.add(Bag(scan.nextInt()))
    }

    backList.sortBy { it.maxWeight }

    //최종 결과값
    var answer: Long = 0L

    backList.forEach { bag ->

        //순회 중 jewelleyQueue가 다 비었다면 prevQueue에서 꺼내서 값을 더한다.
        if (jewelleryQueue.isEmpty()) {
            answer += if (prevPriorityQueue.isEmpty()) 0 else prevPriorityQueue.poll().price
        }

        while (jewelleryQueue.isNotEmpty()) {
            val jewellery = jewelleryQueue.poll()

            // 가방의 사이즈보다 무거우면 순회를 중지하고 prevQueue에 담은 값 중 가장 큰값을 answer값에 더한다.
            if (jewellery.weight > bag.maxWeight) {
                jewelleryQueue.offer(jewellery)
                answer += if (prevPriorityQueue.isEmpty()) 0 else prevPriorityQueue.poll().price
                break
            } else {
                prevPriorityQueue.offer(jewellery)
            }

            // 마지막이며, 주얼리의 무게가 가방 무게보다 가벼울 경우 prevQueue에 담은 값 중 가장 큰값을 answer값에 더한다.
            if (jewelleryQueue.size == 0) {
                answer += if (prevPriorityQueue.isEmpty()) 0 else prevPriorityQueue.poll().price
            }
        }
    }

    println(answer)
}

data class Jewellery(val weight: Int, val price: Int)

data class Bag(val maxWeight: Int)