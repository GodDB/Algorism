import java.util.*

/** https://www.acmicpc.net/problem/2212 */

fun main() {
    val scan = Scanner(System.`in`)
    val sensorN = scan.nextInt() // 센서 갯수
    val centerN = scan.nextInt() // 집중국 갯수
    val sensorList = mutableListOf<Sensor>()

    repeat(sensorN) {
        sensorList.add(Sensor(scan.nextInt()))
    }

    sensorList.sortedBy { it.sensor }
        .forEachRange(0 until sensorN - 1) { index ->
            this[index + 1] - this[index]
        }.sortedDescending()
        .sum(centerN-1)
        .println()

}

private inline fun <T, R> List<T>.forEachRange(range : IntRange, block : List<T>.(Int) -> R) : List<R> {
    val newList : MutableList<R> = mutableListOf()

    for(i in range) {
        newList.add(block(this, i))
    }

    return newList
}

private fun Any.println() = println(this)

private fun List<Int>.sum(startIndex : Int) : Int {
    var result = 0
    for (i in startIndex until size) {
        result += this[i]
    }
    return result
}

private inline class Sensor(val sensor: Int) {
    operator fun minus(other: Sensor): Int {
        return this.sensor - other.sensor
    }
}