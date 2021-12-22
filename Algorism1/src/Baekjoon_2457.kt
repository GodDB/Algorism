import java.time.LocalDate
import java.util.*

/** https://www.acmicpc.net/problem/2457 */
class Baekjoon_2457 {

    var cnt = 0

    private val minDate = LocalDate.of(2020, 3, 1)
    private val maxDate = LocalDate.of(2020, 11, 30)

    fun greedySearch(arr: List<FlowerDate>) {
        var findFlowerDateSet = findGoodDate(0, arr, minDate) ?: return
        cnt++
        var i = findFlowerDateSet.second
        var currentFlowerDate = findFlowerDateSet.first
        while (currentFlowerDate.endData < maxDate.plusDays(1)) {
            val findFlowerDateSet = findGoodDate(i + 1, arr, currentFlowerDate.endData) ?: break

            with(findFlowerDateSet) {
                i = second
                currentFlowerDate = first
                cnt++
            }
        }
        if (currentFlowerDate.endData <= maxDate) cnt = 0
    }

    private fun findGoodDate(startIndex: Int, arr: List<FlowerDate>, date: LocalDate): Pair<FlowerDate, Int>? {
        var maxFlowerDate = FlowerDate.getDefault()
        var maxIndex = 0

        for (i in startIndex until arr.size) {
            val flowerDate = arr[i]
            if (flowerDate.startDate > date) break

            // 탐색 데이터가 입력받은 date보다 빨리 있으면서 그 중 endDate가 가장 긴 애를 찾는다.
            if (flowerDate.startDate <= date) {
                if (maxFlowerDate.endData < flowerDate.endData) {
                    maxFlowerDate = flowerDate
                    maxIndex = i
                }
            }
        }
        return if (maxFlowerDate == FlowerDate.getDefault()) null else maxFlowerDate to maxIndex
    }
}

data class FlowerDate(
    val startDate: LocalDate,
    val endData: LocalDate
) {
    companion object {
        fun getDefault(): FlowerDate =
            FlowerDate(startDate = LocalDate.of(2019, 1, 1), endData = LocalDate.of(2019, 1, 1))
    }
}

fun main() {
    val scan = Scanner(System.`in`)

    val baekjoon2457 = Baekjoon_2457()
    val n = scan.nextInt()
    val flowerDates = mutableListOf<FlowerDate>()

    for (i in 0 until n) {
        val startMonth = scan.nextInt()
        val startDay = scan.nextInt()
        val endMonth = scan.nextInt()
        val endDay = scan.nextInt()
        flowerDates.add(FlowerDate(LocalDate.of(2020, startMonth, startDay), LocalDate.of(2020, endMonth, endDay)))
    }
    flowerDates.sortBy { it.startDate }

    baekjoon2457.greedySearch(flowerDates)
    println(baekjoon2457.cnt)
}