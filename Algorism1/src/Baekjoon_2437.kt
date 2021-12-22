import java.util.*

/** https://www.acmicpc.net/problem/2437 */

fun main() {
    val scan = Scanner(System.`in`)
    val n = scan.nextInt()
    val arr: MutableList<Int> = mutableListOf()

    for (i in 0 until n) {
        arr.add(scan.nextInt())
    }
    arr.sort()

    // 정렬 첫번째 원소가 1이 아니라면 최솟값은 1이다.
    if (arr[0] != 1) {
        println("1")
        return
    }

    // i-1의 누적합 +1보다 i의 값이 더 크다면, i-1 누적합의 +1이 구할수 없는 최솟값이다.
    var sum = 1 // arr[0]
    for (i in 1 until arr.size) {
        if (sum + 1 < arr[i]) {
            break
        }
        sum += arr[i]
    }
    println(sum + 1)
}
