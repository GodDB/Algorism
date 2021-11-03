import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

/** https://www.acmicpc.net/problem/9935 */

fun main() {

    val buffer : BufferedReader = BufferedReader(InputStreamReader(System.`in`))

    val list = buffer.readLine().split("").filter { it != "" && it != " " }
    val boomList = buffer.readLine().split("").filter { it != "" && it != " " }
    val stack = Stack<String>()

    for (i in list.indices) {
        stack.push(list[i])

        if (stack.size >= boomList.size) {
            var flag = true
            for(j in boomList.indices) {
                if(stack[stack.size - boomList.size+j] != boomList[j]){
                    flag = false
                    break
                }
            }

            if (flag) {
                for (k in boomList.indices) {
                    stack.pop()
                }
            }
        }
    }

    stack.let { if(stack.isEmpty()) println("FRULA") else println(stack.joinToString("")) }
}