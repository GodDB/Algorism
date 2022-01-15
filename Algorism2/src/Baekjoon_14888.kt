import kotlin.math.max
import kotlin.math.min

/** https://www.acmicpc.net/problem/14888 */

private var maxValue = Int.MIN_VALUE
private var minValue = Int.MAX_VALUE

fun main() {

    val n = readLine()?.toInt() ?: return

    val arr = readLine()?.split(" ")
        ?.filter { it != "" && it != " " }
        ?.map { it.toInt() } ?: return

    val operatorInputs = readLine()?.split(" ")
        ?.filter { it != "" && it != " " }
        ?.map { it.toInt() } ?: return

    val operator = Operator(operatorInputs[0], operatorInputs[1], operatorInputs[2], operatorInputs[3])

    dfs(n, operator, arr,  1, arr[0])

    println(maxValue)
    println(minValue)
}

private fun dfs(n : Int, operators : Operator, arr : List<Int>, cnt : Int, sum : Int) {
    if(cnt >= n) {
        maxValue = max(sum, maxValue)
        minValue = min(sum, minValue)
        return
    }

    if(operators.plus > 0) {
        dfs(n, operators.copy(plus = operators.plus-1), arr, cnt+1, sum + arr[cnt])
    }
    if(operators.minus > 0) {
        dfs(n, operators.copy(minus = operators.minus-1), arr, cnt+1, sum - arr[cnt])
    }
    if(operators.multiple > 0) {
        dfs(n, operators.copy(multiple = operators.multiple-1), arr, cnt+1, sum * arr[cnt])
    }
    if(operators.division > 0) {
        dfs(n, operators.copy(division = operators.division-1), arr, cnt+1, sum / arr[cnt])
    }

}

data class Operator(val plus : Int, val minus : Int, val multiple : Int, val division : Int)
