import java.util.*

/** https://www.acmicpc.net/problem/2239 */

private val cx2 = listOf(-1, 0, 1, 0)
private val cy2 = listOf(0, -1, 0, 1)

private var result: List<List<Int>>? = null
    set(value) {
        if(field == null) field = value

        field?.forEachIndexed { i, list ->
            list.forEachIndexed { j, itemValue ->
                if(value!![i][j] > itemValue ) return
                else {
                    field = value
                    return
                }
            }
        }
    }

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

    for (i in arr.indices) {
        for (j in arr[i].indices) {
            if (arr[i][j] != 0) {
                val newArr = arr.toMutableList()
                backTracking(i, j, newArr)
                result = newArr
            }
        }
    }

    result?.forEach {
        println(it.joinToString(""))
    }
}

fun backTracking(i: Int, j: Int, arr: MutableList<MutableList<Int>>) {

    for (z in cx2.indices) {
        val newX = i + cx2[z]
        val newY = j + cy2[z]

        //범위를 벗어났는가?
        if (newX < 0 || newX > 8 || newY < 0 || newY > 8) continue
        //값이 지정되어 있는가?
        if (arr[newX][newY] != 0) continue

        for (value in 1..9) {
            var isContinue = false

            //열에 중복되는 숫자가 있는가?
            for(q in 0 until 9) {
                if (arr[newX][q] == value) {
                    isContinue = true
                    break
                }
            }
            if(isContinue) continue

            //행에 중복되는 숫자가 있는가?
            for (q in 0 until 9) {
                if (arr[q][newY] == value) {
                    isContinue = true
                    break
                }
            }
            if(isContinue) continue

            // 3x3 안에 동일한 숫자가 있는가?
            for (q in (newX / 3) * 3 .. ((newX / 3) * 3) + 2) {
                if(isContinue) break
                for (k in (newY / 3) * 3 .. ((newY / 3) * 3) + 2) {
                    if (arr[q][k] == value) {
                        isContinue = true
                        break
                    }
                }
            }

            if(isContinue) continue
            arr[newX][newY] = value
            break
        }

        backTracking(newX, newY, arr)
    }
}