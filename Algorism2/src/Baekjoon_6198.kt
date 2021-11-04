import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

/** https://www.acmicpc.net/problem/6198 */

// monoton stack
fun main() {
    val buffer : BufferedReader = BufferedReader(InputStreamReader(System.`in`))
    val n = buffer.readLine().toInt()
    val arr = mutableListOf<Int>()
    val stack = Stack<Int>()
    for(i in 0 until n) {
        arr.add(buffer.readLine().toInt())
    }

    var count : Long = 0
    for(i in 0 until n) {
        while (stack.isNotEmpty() && stack.peek() <= arr[i]) {
           stack.pop()
        }
        count += stack.size
        stack.push(arr[i])
    }
    println(count)
}