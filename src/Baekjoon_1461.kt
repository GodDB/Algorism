import java.util.*
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

/** https://www.acmicpc.net/problem/1461 */


fun main() {
    val scan = Scanner(System.`in`)
    var ans = 0

    val n = scan.nextInt()
    val m = scan.nextInt()
    val plusArr = mutableListOf<Int>()
    val minusArr = mutableListOf<Int>()

    for (i in 0 until n) {
        val value = scan.nextInt()
        if (value >= 0) plusArr.add(value)
        else minusArr.add(value)
    }
    //오름차순
    plusArr.sortDescending()
    //내림차순
    minusArr.sort()

    for (i in plusArr.indices step m) {
        ans += plusArr[i] * 2
    }

    for (i in minusArr.indices step m) {
        ans += abs(minusArr[i]) * 2
    }

    ans -= max(plusArr.firstOrNull() ?: 0, abs(minusArr.firstOrNull() ?: 0))
    println(ans)
}

