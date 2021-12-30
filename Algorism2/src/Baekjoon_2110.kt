import java.util.*

/** https://www.acmicpc.net/problem/2110 */
class Baekjoon_2110 {

    var routerCount = 0
    var distance = 0

    /** distance에 대한 이분탐색을 실시한다. 이분 탐색을 하지 않고 반복문을 통해 1부터 distance를 측정한다면 n^2이므로 타임아웃 발생
     * 이분 탐색을 통해 distance를 log2N으로 탐색한다면 log(N) * N이므로 타임아웃 발생하지 않는다
     */
    fun binarySearch(minDistance: Int, maxDistance: Int, arr: List<Int>) {
        if (minDistance > maxDistance) return

        val targetDistance = (minDistance + maxDistance) / 2
        var count = 1
        var start = arr.first()

        arr.asSequence()
            .filter { it - start >= targetDistance }
            .forEach {
                count++
                start = it
            }

        if (count >= routerCount) {
            distance = targetDistance
            binarySearch(targetDistance + 1, maxDistance, arr)
        } else {
            binarySearch(minDistance, targetDistance - 1, arr)
        }
    }
}

fun main() {
    val scan = Scanner(System.`in`)
    val n = scan.nextInt()
    val routerCount = scan.nextInt()
    val arr = mutableListOf<Int>()

    repeat(n) {
        arr.add(scan.nextInt())
    }
    arr.sort()

    Baekjoon_2110()
        .apply { this.routerCount = routerCount }
        .run {
            binarySearch(1, arr.last() - arr.first(), arr)
            println(this.distance)
        }

}