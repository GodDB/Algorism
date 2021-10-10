import java.util.*
import kotlin.math.abs
import kotlin.system.exitProcess

/**
 * https://www.acmicpc.net/problem/1107
 * 이 문제는 단순 target 값에서 + -로 이동했을 때와,
 * 0 ~ 1000000까지 반복문을 통해 가장 channel 값과 가까운 값과의 차이를 구해서 둘 중 작은 값을 선택하면 해결
 */

class Baekjoon_1107 {

    lateinit var brokenButtons: List<Int>
    var targetValue = 0
    var minCount = Integer.MAX_VALUE
        set(value) {
            field = field.coerceAtMost(value)
        }

    fun searchRemocon() {
        minCount = abs(targetValue - 100)  // +- 로만 갔을 경우에 이동값

        //번호를 눌러서 갔을 때 최소 이동값
        for (i in 0 until 1000000) {
            val count = solve(i)

            if (count == 0) continue
            if (i == targetValue) minCount = count

            // +- 잔여값 까지
            minCount = count + abs(targetValue - i)
        }
    }

    private fun solve(value: Int): Int {
        val valueStr = value.toString()
        var count = 0
        valueStr.forEach { char ->
            if (brokenButtons.contains(char - '0')) {
                return 0
            } else {
                count++
            }
        }
        return count
    }
}

fun main() {
    val scan = Scanner(System.`in`)

    val targetValue = scan.nextInt() //5457
    val brokenButton = mutableListOf<Int>()

    for (i in 0 until scan.nextInt()) {
        brokenButton.add(scan.nextInt())
    }

    Baekjoon_1107().apply {
        this.brokenButtons = brokenButton
        this.targetValue = targetValue
    }.run {
        searchRemocon()
        println(minCount)
    }

}