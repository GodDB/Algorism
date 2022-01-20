import java.util.*

/** https://www.acmicpc.net/problem/11000 */

/**
 * 시작 시간이 빠른순으로 정렬되는 우선순위 큐와 끝나는 시간이 빠른순으로 정렬되는 우선순위 큐 2개를 이용해서 해결한다.
 * 시작 시간이 빠른 순으로 정렬되는 우선순위 큐에서 item을 하나씩 빼서 끝나는 시간이 빠른 우선순위 큐의 값과 비교하여 시작시간이 끝나는 시간 뒤라면
 * 끝나는 시간이 빠른 큐에서 하나 빼고, 하나를 새롭게 넣는다 (즉 같은 강의실을 사용하는 것 )
 *
 * 만약 위의 조건에 부합하지 않으면 빼지않고 넣는다 (즉 다른 강의실을 사용하는 것)
 *
 * 최종적으로 끝나는 시간이 빠른 큐의 사이즈를 측정하면 그것이 강의실을 사용한 갯수이다.
 */
fun main() {
    val scan = Scanner(System.`in`)

    val startPriorQueue = PriorityQueue<Study> { o1, o2 ->
        if(o1.startTime == o2.startTime) o1.endTime - o2.endTime
        else o1.startTime - o2.startTime
    }

    val endPriorQueue = PriorityQueue<Study> { o1, o2 -> o1.endTime - o2.endTime}


    val n = scan.nextInt()

    repeat(n) {
        startPriorQueue.offer(Study(scan.nextInt(), scan.nextInt()))
    }

    while (startPriorQueue.isNotEmpty()) {
        val study = startPriorQueue.poll()

        if(endPriorQueue.isEmpty()){
            endPriorQueue.offer(study)
            continue
        }

        if(study.startTime >= endPriorQueue.peek().endTime) {
            endPriorQueue.poll()
            endPriorQueue.offer(study)
        } else {
            endPriorQueue.offer(study)
        }
    }

    println(endPriorQueue.size)

}



data class Study(val startTime : Int, val endTime : Int)