import java.util.*

/** https://www.acmicpc.net/problem/11000 */
// 다시 풀기

data class StudyClass(val startTime: Int, val endTime: Int)

fun main() {
    val scan = Scanner(System.`in`)

    val n = scan.nextInt()

    // startTime 기준 오름차순
    val priorQueue: PriorityQueue<StudyClass> = PriorityQueue { old, new ->
        if (old.startTime == new.startTime) old.endTime - new.endTime
        else old.startTime - new.startTime
    }

    // endTime 기준 오름차순
    val priorQueue2 : PriorityQueue<StudyClass> = PriorityQueue { old, new ->
        old.endTime - new.endTime
    }

    for (i in 0 until n) {
        priorQueue.add(StudyClass(scan.nextInt(), scan.nextInt()))
    }

    priorQueue2.add(priorQueue.poll())
    while (priorQueue.isNotEmpty()) {
        val nextStudyClass = priorQueue.poll()

        if(nextStudyClass.startTime >= priorQueue2.peek().endTime) {
            priorQueue2.poll()
        }
        priorQueue2.offer(nextStudyClass)
    }
    print("${priorQueue2.size}")
}