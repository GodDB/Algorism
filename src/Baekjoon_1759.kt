import java.util.*

/** https://www.acmicpc.net/problem/1759 */

private val vowelList: List<Char> = listOf('a', 'e', 'i', 'o', 'u')

fun main() {

    val scan = Scanner(System.`in`)

    val n = scan.nextInt()
    val m = scan.nextInt()
    val arr = mutableListOf<Char>()
    val result = MutableList(n) { 'a' }
    val visitList = MutableList(m) { false }
    for (i in 0 until m) {
        arr.add(scan.next().first())
    }
    arr.sort()
    backTracking(0, 0, arr, result, visitList, n, m)
}

private fun backTracking(
    startIndex: Int,
    prevIndex: Int,
    arr: MutableList<Char>,
    result: MutableList<Char>,
    visitList: MutableList<Boolean>,
    n: Int,
    m: Int
) {
    if (startIndex == n) {
        var vowelCnt = 0
        var consonantCnt = 0
        result.forEach { value ->
            if (vowelList.contains(value)) vowelCnt++
            else consonantCnt++
        }

        if (vowelCnt >= 1 && consonantCnt >= 2) {
            println(result.joinToString(""))
        }
        return
    }

    for (i in prevIndex until m) {
        if (visitList[i]) continue
        visitList[i] = true
        result[startIndex] = arr[i]
        backTracking(startIndex + 1, i + 1, arr, result, visitList, n, m)
        visitList[i] = false
    }
}