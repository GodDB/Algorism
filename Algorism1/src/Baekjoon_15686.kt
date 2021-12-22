import java.util.*
import kotlin.math.abs
import kotlin.math.min

class Baekjoon_15686 {
    var m: Int = 0 // 폐업 시키지 않을 치킨 집의 수
    var n: Int = 0 // arr max langth

    lateinit var chickenList : List<Point6>
    lateinit var homeList : List<Point6>
    val selectChickenList : MutableList<Point6> = mutableListOf()

    var minChickenStreet: Int = Integer.MAX_VALUE
        set(value) {
            field = field.coerceAtMost(value)
        }

    fun shutDownChickenRestaurant(startIndex : Int,  notShutDownCount: Int) {
        if (notShutDownCount == m) {
            calculateChickenStreet()
            return
        }

        for(i in startIndex until chickenList.size) {
            selectChickenList.add(chickenList[i])
            shutDownChickenRestaurant(i+1, notShutDownCount+1)
            selectChickenList.remove(chickenList[i])
        }
    }

    private fun calculateChickenStreet() {
        var streetCountSum = 0
        homeList.forEach { home ->
            var streetCount = Integer.MAX_VALUE
            selectChickenList.forEach { chicken ->
                val value = abs(home.x - chicken.x) + abs(home.y - chicken.y)
                streetCount = min(streetCount, value)
            }
            streetCountSum += streetCount
        }
        minChickenStreet = streetCountSum
    }
}

data class Point6(val x: Int, val y: Int)

fun main() {

    val scan = Scanner(System.`in`)
    val n = scan.nextInt()
    var m = scan.nextInt()

    val chickenList = mutableListOf<Point6>()
    val homeList = mutableListOf<Point6>()

    for (i in 0 until n) {
        for (j in 0 until n) {
            val value = scan.nextInt()
            if(value == 1) homeList.add(Point6(i, j))
            if(value == 2) chickenList.add(Point6(i, j))
        }
    }
    Baekjoon_15686().apply {
        this.m = m
        this.n = n
        this.chickenList = chickenList
        this.homeList = homeList
    }.run {
        shutDownChickenRestaurant(0, 0)
        println(minChickenStreet)
    }
}
