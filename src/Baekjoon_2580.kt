import java.util.*
import kotlin.system.exitProcess

/** https://www.acmicpc.net/problem/2580 */

fun main() {
    val scan = Scanner(System.`in`)

    val n = 9
    val m = 9
    val arr = MutableList(n) { MutableList(m) { 0 } }

    for (i in 0 until n) {
        for (j in 0 until m) {
            arr[i][j] = scan.nextInt()
        }
    }

    backTracking1(0, 0, arr)
}

private fun backTracking1(x: Int, y: Int, arr: MutableList<MutableList<Int>>) {
    if (y == 9) {
        backTracking1(x + 1, 0, arr)
        return
    }

    if (x == 9) {
        printResult(arr)
        exitProcess(0)
    }

    for (value in 1..9) {
        //이미 값이 있다면 다음을 검색한다.
        if (arr[x][y] != 0) {
            backTracking1(x, y+1, arr)
            return
        }

        if(check1(x, y, arr, value)) {
            arr[x][y] = value
            backTracking1(x, y+1, arr)
            arr[x][y] = 0
        }

    }
}

private fun printResult(arr : List<List<Int>>) {
    arr.forEach {
        println(it.joinToString(" "))
    }
}

private fun check1(x: Int, y: Int, arr: MutableList<MutableList<Int>>, value: Int): Boolean {

    for (i in 0 until 9) {
        // 같은 행에 동일한 값이 있는가?
        if (arr[i][y] == value) return false
        // 같은 열에 동일한 값이 있는가?
        if (arr[x][i] == value) return false
    }

    // 3x3안에 같은 값이 있는가?
    val minX = (x / 3) * 3
    val minY = (y / 3) * 3

    for (i in minX until minX + 3) {
        for (j in minY until minY + 3) {
            if (arr[i][j] == value) return false
        }
    }
    return true
}