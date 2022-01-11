import java.util.*
import kotlin.math.abs

/** https://www.acmicpc.net/problem/8983 */
class Baekjoon_8983 {

    /** 먹잇감이 자기를 먹어줄 사냥꾼을 찾는 이분탐색 */
    fun binarySearch(target: Target, shooterList: List<Int>, range: Int, _start: Int, _end: Int): Int {

        var start = _start
        var end = _end

        while (start <= end) {
            val mid = (start + end) / 2
            val midValue = shooterList[mid]

            val value = abs(midValue - target.x) + target.y

            //찾았다
            if (value <= range) return 1

            // 먹잇감이 사냥꾼보다 왼쪽에 있다면 왼쪽으로 탐색을 이어간다.
            if (target.x < midValue) {
                end = mid - 1
            }
            // 먹잇감이 사냥꾼보다 오른쪽에 있다면 오른쪽으로 탐색을 이어간다.
            else {
                start = mid + 1
            }
        }

        //못찾으면 0
        return 0
    }
}

fun main() {
    val scan = Scanner(System.`in`)

    val n = scan.nextInt()
    val m = scan.nextInt()
    val range = scan.nextInt()

    val shooterList = mutableListOf<Int>()
    val targetList = mutableListOf<Target>()

    repeat(n) {
        shooterList.add(scan.nextInt())
    }

    repeat(m) {
        targetList.add(Target(scan.nextInt(), scan.nextInt()))
    }

    shooterList.sort()

    var answer = 0
    Baekjoon_8983().run {
        targetList.forEach { target ->
            answer += binarySearch(target, shooterList, range, 0, n - 1)
        }
    }
    println(answer)
}

data class Target(val x: Int, val y: Int)