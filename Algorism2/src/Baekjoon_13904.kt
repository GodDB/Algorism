import java.util.*
import kotlin.math.max

/** https://www.acmicpc.net/problem/13904 */
fun main() {
    val scan = Scanner(System.`in`)

    val n = scan.nextInt()
    val arr = mutableListOf<Task>()
    var currentDay = Int.MIN_VALUE

    repeat(n) {
        val task = Task(scan.nextInt(), scan.nextInt())
        arr.add(task)
        currentDay = max(task.remainingDay, currentDay)
    }

    var answer = 0
    while (currentDay > 0) {
        findMaxCompensation(arr, currentDay)?.also { task ->
            answer += task.compensation
            arr.remove(task)
        }
        currentDay--
    }

    println(answer)
}

/**
 * 최소 남은 일 수보다 크거나 같으면서 보상이 가장 큰 값을 조회한다.
 *
 * @param remainingDay - 최소 남은 일 수
 * @return 최소 남은 일 수보다 크거나 같으면서 보상이 가장 큰 값
 */
private fun findMaxCompensation(arr: List<Task>, remainingDay: Int): Task? {
    var resultTask: Task? = null
    arr.forEach { task ->
        if (task.remainingDay >= remainingDay) {
            resultTask = if (resultTask == null) task else task.compare(resultTask!!) { o1, o2 ->
                o2.compensation - o1.compensation
            }
        }
    }

    return resultTask
}


data class Task(val remainingDay: Int, val compensation: Int)

private inline fun Task.compare(other: Task, block: (Task, Task) -> Int): Task {
    return if (block(this, other) > 0) other else this
}