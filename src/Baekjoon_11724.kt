import java.util.*

/** https://www.acmicpc.net/problem/11724 */

fun main() {

    val scan = Scanner(System.`in`)

    val n = scan.nextInt()
    val m = scan.nextInt()

    val arr: MutableList<MutableList<Int>> = MutableList(n + 1) { mutableListOf() }
    val visitList: MutableList<Boolean> = MutableList(n + 1) { false }
    val resultList : MutableList<Int> = MutableList(n+1) {0 }

    for (i in 0 until m) {
        val startNode = scan.nextInt()
        val endNode = scan.nextInt()

        arr[startNode].add(endNode)
        arr[endNode].add(startNode)
    }

    for (i in 1..n) {
        dfs(arr, visitList, resultList, i, i)
    }

    println("${resultList.sum()}")
}

fun dfs(arr: List<List<Int>>, visitList: MutableList<Boolean>, resultList : MutableList<Int>, startIndex: Int, targetIndex : Int) {
    visitList[startIndex] = true

    for (node in arr[startIndex]) {
        if (!visitList[node]) {
            dfs(arr, visitList, resultList, node, targetIndex)
            resultList[targetIndex] = 1
        }
    }
}