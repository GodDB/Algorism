import kotlin.math.max

/** https://www.acmicpc.net/problem/14501 */
private var maxPay : Int = 0

fun main() {
    val n = readLine()?.toInt() ?: return
    val arr : MutableList<Counselling> = mutableListOf()

    repeat(n) {
        val input = readLine()?.split(" ")?.filter { it != "" && it != " " } ?: return
        arr.add(Counselling(input[0].toInt(), input[1].toInt()))
    }

    arr.forEachIndexed { index, counselling ->
        dfs(n, arr, index, counselling.pay, index+1 + counselling.time)
    }

    println(maxPay)
}

private fun dfs(n : Int, arr : List<Counselling>, startIndex : Int, sumPay : Int, minTime : Int) {
    if(minTime <= n+1) {
        maxPay = max(maxPay, sumPay)
    } else {
        return
    }

    for(i in startIndex+1 until n) {
        if(minTime <= i+1) {
            dfs(n, arr, i, sumPay + arr[i].pay, i+1 + arr[i].time)
        }
    }
}

data class Counselling(val time : Int, val pay : Int)
