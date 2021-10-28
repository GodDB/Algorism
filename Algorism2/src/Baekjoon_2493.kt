import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*


fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val n = br.readLine().toInt()
    val stack = Stack<Int>()

    val arr = br.readLine().split(" ")
        .asSequence()
        .filter { it != "" && it != " " }
        .map { it.toInt() }
        .toMutableList()

    // O(N)
    for (i in n - 1 downTo 0) {
        val value = arr[i]

        if (stack.isEmpty()) {
            stack.push(i)
        } else {
            while (stack.isNotEmpty()) {
                val stackValueIndex = stack.peek()

                if (value <= arr[stackValueIndex]) {
                    stack.push(i)
                    break
                } else {
                    arr[stackValueIndex] = i + 1
                    stack.pop()
                }
            }
            if (stack.isEmpty()) stack.push(i)
        }
    }

    while (stack.isNotEmpty()) {
        arr[stack.pop()] = 0
    }

    bw.write(arr.joinToString(" "))
    bw.flush()
    bw.close()
    br.close()
}