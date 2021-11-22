import java.util.*
import kotlin.math.max

/** https://www.acmicpc.net/problem/15926 */

var maxCount = Int.MIN_VALUE
    set(value) {
        field = value.coerceAtLeast(field)
    }

fun main() {
    val scan = Scanner(System.`in`)
    val n = scan.nextInt()
    val arr = scan.next().split("").filter { it != " " && it != "" }
    val stack = Stack<Int>()
    val check = MutableList(n) { false }

    for (i in arr.indices) {
        if (arr[i] == "(") {
            stack.push(i)
        }
        // value가 )일 때
        else {
            if(stack.isNotEmpty()) {
               val index = stack.pop()
                check[index] = true
                check[i] = true
            }
        }
    }

    var count = 0
    for(i in check.indices) {
        if(check[i]) {
            count++
        } else {
            maxCount = count
            count = 0
        }
    }
    maxCount = count
    println(maxCount)
}