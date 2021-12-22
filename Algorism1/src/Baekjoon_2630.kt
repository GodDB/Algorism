import java.util.*

/**
 * https://www.acmicpc.net/problem/2630
 * */

var whiteCnt : Int = 0
var blueCnt : Int = 0

fun main() {

    val scan: Scanner = Scanner(System.`in`)

    val n = scan.nextInt()

    val arr: MutableList<MutableList<Int>> = MutableList(n) { MutableList(n){0} }

    for (i in 0 until n) {
        for (j in 0 until n) {
            val value = scan.nextInt()
            arr[i][j] = value
        }
    }

    recursionSearch(arr, 0, n-1, 0, n-1)
    println("$whiteCnt")
    println("$blueCnt")
}

private fun recursionSearch(arr : List<List<Int>>, fromI: Int, toI: Int, fromJ: Int, toJ: Int) {
    if(fromI > toI || fromJ > toJ) return


    if(checkIsEquals(arr, fromI, toI, fromJ, toJ)) {
        if(arr[fromI][fromJ] == 0) {
            whiteCnt ++
        } else {
            blueCnt ++
        }
    } else {
        recursionSearch(arr, fromI, (fromI+toI)/2, fromJ, (fromJ+toJ)/2) //1사분면
        recursionSearch(arr, ((fromI+toI)/2)+1, toI, fromJ, (fromJ+toJ)/2) //2사분면
        recursionSearch(arr, fromI, (fromI+toI)/2, ((fromJ + toJ)/2) +1 , toJ) //3사분면
        recursionSearch(arr, ((fromI+toI)/2)+1, toI, ((fromJ+toJ)/2)+1, toJ) //4사분면
    }
}

private fun checkIsEquals(arr: List<List<Int>>, fromI: Int, toI: Int, fromJ: Int, toJ: Int) : Boolean {
    val tempValue : Int = arr[fromI][fromJ]
    for (i in fromI..toI) {
        for (j in fromJ..toJ) {
            val value = arr[i][j]
            if(value != tempValue) {
                return false
            }
        }
    }
    return true
}
