import java.util.*

/** https://www.acmicpc.net/problem/1799 */

private data class Point3(val x: Int, val y: Int)

private var result: Int = Int.MIN_VALUE
    set(value) {
        field = field.coerceAtLeast(value)
    }

fun main() {
    val scan = Scanner(System.`in`)
    val n = scan.nextInt()

    val checkList = MutableList(n) { MutableList(n) { false } }
    val inputList = MutableList(n) { MutableList(n) { 0 } }

    for (i in 0 until n) {
        for (j in 0 until n) {
            inputList[i][j] = scan.nextInt()
        }
    }

    backTracking(Point3(0, 0), inputList, checkList, 0, n)
    println("$result")
}

private fun backTracking(
    point: Point3,
    arr: List<List<Int>>,
    visitList: MutableList<MutableList<Boolean>>,
    sum: Int,
    max: Int
) {
    // 모든 순회가 끝났다.
    if (point.y >= max) {
        result = sum
        return
    }

    for (i in point.x .. max) {
        // x값이 index를 넘었다.
        if (i == max) {
            backTracking(point.copy(x = 0, y = point.y + 1), arr, visitList, sum, max)
            return
        }

        if (diagonalLine(i, point.y, visitList) || visitList[i][point.y] || arr[i][point.y] == 0) {
            continue
        }

        visitList[i][point.y] = true
        backTracking(point.copy(x = i + 1), arr, visitList, sum + 1, max)
        visitList[i][point.y] = false
    }
}

private fun diagonalLine(x: Int, y: Int, visitList: List<List<Boolean>>): Boolean {
    for (i in 0 .. x) {
        for (j in 0 .. y) {
            if(i == x && j == y) return false
            //방문 했으며, 대각선일 경우 true
            if (visitList[i][j] && ((i - j == x - y) || (i + j == x + y))) return true
        }
    }
    return false
}