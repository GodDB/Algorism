import java.util.*

/** https://www.acmicpc.net/problem/1339 */
fun main() {

    val scan = Scanner(System.`in`)
    val n = scan.nextInt()

    val alphabetMap = mutableMapOf<String, Int>()
    for (i in 0 until n) {
        val valueArr = scan.next().split("").filter { it != "" }
        valueArr.reversed().forEachIndexed { index, s ->
            alphabetMap[s] = (alphabetMap[s] ?: 0) + sqrt(index)
        }
    }

    var value = 10
    var result = 0
    alphabetMap.toList().sortedByDescending { it.second }.forEach {
        result += it.second * --value
    }
    println("$result")
}

private fun sqrt(value : Int) : Int {
    var result : Int = 1
    for(i in 0 until value) {
        result *= 10
    }
    return result
}