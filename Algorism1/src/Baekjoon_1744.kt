import java.util.*
import kotlin.math.min

/** https://www.acmicpc.net/problem/1744 */

const val NOT_VALUE: Int = Int.MIN_VALUE

fun main() {
    val scan = Scanner(System.`in`)
    val n = scan.nextInt()
    val plusArr = mutableListOf<Int>()
    val minusArr = mutableListOf<Int>()

    var result = 0
    for (i in 0 until n) {
        val value = scan.nextInt()
        if (value == 1) result += value
        else if (value > 1 || value == 0) plusArr.add(value)
        else minusArr.add(value)
    }

    plusArr.sortDescending()
    minusArr.sort()

    //직전값 현재값이 2 이상일 경우엔 무조건 곱한다.
    //직전값 현재값중 하나가 0 일 경우엔 더한다.
    var delegateValue = NOT_VALUE
    for (i in 0 until plusArr.size step 2) {
        //현재 인덱스가 초과했고 직전 값이 0이 아니라면 그냥 더한다.
        if (i + 1 >= plusArr.size && plusArr[i] != 0) {
            result += plusArr[i]
            break
        }
        //현재 인덱스가 초과했고, 직전 값이 0이라면 위임한다.
        if (i + 1 >= plusArr.size && plusArr[i] == 0) {
            delegateValue = plusArr[i]
            break
        }
        val value1 = plusArr[i]
        val value2 = plusArr[i + 1]

        //현재 값이 0이라면 직전값은 더하고 현재값은 위임한다.
        if (value2 == 0) {
            result += value1
            delegateValue = value2
        } else {
            result += (value1 * value2)
        }
    }

    var minusEndIndex = if (delegateValue == 0 && minusArr.size % 2 != 0) minusArr.size - 1 else minusArr.size

    for (i in 0 until minusEndIndex step 2) {
        if (i + 1 >= minusEndIndex) {
            result += minusArr[i]
            break
        }
        result += minusArr[i] * minusArr[i + 1]
    }
    println("$result")

}