import java.util.*

/** https://www.acmicpc.net/problem/2437 */
fun main() {
    val scan = Scanner(System.`in`)

    val n = scan.nextInt()
    val arr = mutableListOf<Int>()

    repeat(n) {
        arr.add(scan.nextInt())
    }

    arr.sort()

    //첫번째 값이 1이 아니면 최솟값은 1이다.
    if (arr.first() != 1) {
        return println(1)
    }

    //누적합+1 보다 i값이 크면 list[i]+1값이 못구하는 수 이다.
    var sum: Int = arr.first()
    for (i in 1 until arr.size) {
        if (sum + 1 < arr[i]) {
            break
        }
        sum += arr[i]
    }

    //마지막까지 더해보고도 결과가 없는 경우가 있다.
    println(sum + 1)
}