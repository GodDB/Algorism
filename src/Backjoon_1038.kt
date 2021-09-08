import java.util.*
import kotlin.system.exitProcess

/** https://www.acmicpc.net/problem/1038 */

private var sum = 9

fun main() {
    val scan = Scanner(System.`in`)
    val n = scan.nextInt()

    if (n <= 9) {
        println("$n")
    } else {
        backTracking(9, n)
    }
}

private fun backTracking(value: Int, max: Int) {

    var i = value
    while (true) {
        if (i == Integer.MAX_VALUE) {
            println("-1")
            return
        }
        if (sum == max) {
            println("$i")
            return
        }

        val jarisoo = check(i)

        if(jarisoo == -1) {
            sum++
            i++
        } else {
            val squrt10 = getSqurt10(jarisoo)
            i = (i / squrt10) * squrt10 + squrt10
        }
    }
}

private fun check(value: Int): Int {
    val newValue = value.toString()
    val length = newValue.length - 1
    for (i in 0 until newValue.length - 1) {
        if (newValue[length - i] >= newValue[length - i - 1]) return i
    }
    return -1
}

private fun getSqurt10(value : Int) : Int {
    return if(value == 0) 1
    else {
        var value = 10
        for( i in 1 until value) {
            value *= value
        }
        value
    }
}