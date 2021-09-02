import java.util.*
import kotlin.system.exitProcess

/** https://www.acmicpc.net/problem/2239 */
fun main() {

    val scan = Scanner(System.`in`)
    val arr = MutableList(9) { mutableListOf<Int>() }

    for (i in 0 until 9) {
        val ints = scan.next().split("")
        ints.forEach {
            if (it != "") {
                arr[i].add(it.toInt())
            }
        }
    }
    backTracking(0, 0, arr)
}

fun backTracking(i: Int, j: Int, arr: MutableList<MutableList<Int>>) {
    if (j == 9) {
        backTracking(i + 1, 0, arr)
        return
    }
    if (i == 9) {
        arr.forEach { println(it.joinToString("")) }
        exitProcess(0)
    }

    for (value in 1..9) {

        //값이 지정되어 있는가?
        if (arr[i][j] != 0) {
            backTracking(i, j + 1, arr)
            return
        }

        if (check(i, j, arr, value)) {
            arr[i][j] = value
            backTracking(i, j + 1, arr)
            arr[i][j] = 0
        }
    }
}

private fun check(i: Int, j: Int, arr: List<List<Int>>, value: Int): Boolean {
    //열에 중복되는 숫자가 있는가?
    for (q in 0 until 9) {
        if (arr[i][q] == value) {
            return false
        }
    }

    //행에 중복되는 숫자가 있는가?
    for (q in 0 until 9) {
        if (arr[q][j] == value) {
            return false
        }
    }

    // 3x3 안에 동일한 숫자가 있는가?
    for (q in (i / 3) * 3..((i / 3) * 3) + 2) {
        for (k in (j / 3) * 3..((j / 3) * 3) + 2) {
            if (arr[q][k] == value) {
                return false
            }
        }
    }

    return true
}