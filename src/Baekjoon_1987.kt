import java.util.*

/** https://www.acmicpc.net/problem/1987 */

private val cx1 = listOf(-1, 0, 1, 0)
private val cy1 = listOf(0, -1, 0, 1)

private var result = Integer.MIN_VALUE
    set(value) {
        field = value.coerceAtLeast(field)
    }

fun main() {

    val scan = Scanner(System.`in`)
    val r = scan.nextInt()
    val c = scan.nextInt()

    val arr = MutableList(r) { mutableListOf<Char>() }
    val visitList = mutableSetOf<Char>()

    for (i in 0 until r) {
        val charList = scan.next().toCharArray()
        charList.forEach {
            arr[i].add(it)
        }
    }

    visitList.add(arr[0][0])
    backTracking1(0, 0, r, c, arr, visitList, 0)
    println("$result")

}

private fun backTracking1(i: Int, j: Int, r: Int, c: Int, arr: List<List<Char>>, visitList: MutableSet<Char>, sum : Int) {
    val newSum = sum + 1
    result = newSum

    for(z in cx1.indices) {
        val nx = i + cx1[z]
        val ny = j + cy1[z]
        if(nx >= r || nx < 0 || ny >= c || ny < 0) continue
        val value = arr[nx][ny]
        if(visitList.contains(value)) continue
        visitList.add(value)
        backTracking1(nx, ny, r, c, arr, visitList, newSum)
        visitList.remove(value)
    }
}
