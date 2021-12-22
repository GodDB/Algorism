import java.util.*

class Baekjoon_14500 {
    var n: Int = 0
    var m: Int = 0

    val cx = listOf<Int>(-1, 0, 1, 0)
    val cy = listOf<Int>(0, -1, 0, 1)

    var maxValue: Int = Integer.MIN_VALUE
        set(value) {
            field = field.coerceAtLeast(value)
        }

    // ㅗ 모양 제외
    fun tetromino(
        arr: MutableList<MutableList<Int>>,
        visitList: MutableList<MutableList<Boolean>>,
        i: Int,
        j: Int,
        step: Int,
        sum: Int
    ) {
        if (step == 4) {
            maxValue = sum
            return
        }

        for (k in 0 until 4) {
            val newX = i + cx[k]
            val newY = j + cy[k]

            if (newX < 0 || newX >= n || newY < 0 || newY >= m) continue
            if (visitList[newX][newY]) continue

            visitList[newX][newY] = true
            tetromino(arr, visitList, newX, newY, step + 1, sum + arr[newX][newY])
            visitList[newX][newY] = false
        }
    }

    val ㅓ_x = listOf(0, 1, 2, 1)
    val ㅓ_y = listOf(0, 0, 0, -1)
    val ㅏ_x = listOf(0, 1, 2, 1)
    val ㅏ_y = listOf(0, 0, 0, 1)
    val ㅗ_x = listOf(0, 1, 1, 1)
    val ㅗ_y = listOf(0, -1, 0, 1)
    val ㅜ_x = listOf(0, 0, 0, 1)
    val ㅜ_y = listOf(0, 1, 2, 1)

    val fuckX = listOf(ㅓ_x, ㅏ_x, ㅗ_x, ㅜ_x)
    val fuckY = listOf(ㅓ_y, ㅏ_y, ㅗ_y, ㅜ_y)

    // ㅗ, ㅓ, ㅏ, ㅜ 모양 조회
    fun searchFuckYou(arr: List<List<Int>>, i: Int, j: Int) {
        for (k in 0 until 4) {
            var sum = 0
            var isOk = true
            for (z in 0 until 4) {
                val newX = fuckX[k][z] + i
                val newY = fuckY[k][z] + j

                if (newX < 0 || newX >= n || newY < 0 || newY >= m) {
                    isOk = false
                    break
                }
                sum += arr[newX][newY]
            }
            if (isOk) maxValue = sum
        }
    }
}

fun main() {
    val scan = Scanner(System.`in`)
    val n = scan.nextInt()
    val m = scan.nextInt()

    val arr: MutableList<MutableList<Int>> = MutableList(n) { MutableList(m) { 0 } }
    val visitList: MutableList<MutableList<Boolean>> = MutableList(n) { MutableList(m) { false } }
    for (i in 0 until n) {
        for (j in 0 until m) {
            arr[i][j] = scan.nextInt()
        }
    }

    Baekjoon_14500().apply {
        this.n = n
        this.m = m
    }.run {
        for (i in 0 until n) {
            for (j in 0 until m) {
                visitList[i][j] = true
                tetromino(arr, visitList, i, j, 1, arr[i][j])
                visitList[i][j] = false
                searchFuckYou(arr, i, j)
            }
        }
        println(maxValue)
    }
}