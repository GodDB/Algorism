import java.util.*

/** https://www.acmicpc.net/problem/19942 */

data class Diet(val protein: Int, val fat: Int, val carbohydrate: Int, val vitamin: Int, val price: Int) {

    companion object {
        fun createEmpty(): Diet = Diet(0, 0, 0, 0, 0)
    }
}

operator fun Diet.plus(other: Diet): Diet {
    return Diet(
        protein = this.protein + other.protein,
        fat = this.fat + other.fat,
        carbohydrate = this.carbohydrate + other.carbohydrate,
        vitamin = this.vitamin + other.vitamin,
        price = this.price + other.price
    )
}

fun Diet.isMoreThan(other: Diet): Boolean {
    return this.protein >= other.protein && this.fat >= other.fat && this.carbohydrate >= other.carbohydrate && this.vitamin >= other.vitamin
}

var result1: Pair<Diet, String>? = null
    set(value) {
        if (field == null) {
            field = value
            return
        }

        if (field!!.first.price <= value!!.first.price) {
            return
        } else {
            field = value
        }
    }

fun main() {
    val scan = Scanner(System.`in`)

    val n = scan.nextInt()
    val arr = mutableListOf<Diet>()
    val visitList = MutableList(n) { false }

    val targetDiet = Diet(
        protein = scan.nextInt(),
        fat = scan.nextInt(),
        carbohydrate = scan.nextInt(),
        vitamin = scan.nextInt(),
        price = 0
    )
    for (i in 0 until n) {
        arr.add(
            Diet(
                protein = scan.nextInt(),
                fat = scan.nextInt(),
                carbohydrate = scan.nextInt(),
                vitamin = scan.nextInt(),
                price = scan.nextInt()
            )
        )
    }

    backTracking(0, Diet.createEmpty(), "", targetDiet, visitList, arr)

    if (result1 == null) {
        println("-1")
    } else {
        println("${result1!!.first.price}")
        println(result1!!.second.removePrefix(" "))
    }
}

private fun backTracking(
    startIndex: Int,
    sumDiet: Diet,
    sumValue: String,
    targetDiet: Diet,
    visitList: MutableList<Boolean>,
    arr: List<Diet>
) {

    if (sumDiet.isMoreThan(targetDiet)) {
        result1 = sumDiet to sumValue
        return
    }

    for (i in startIndex until visitList.size) {
        if (visitList[i]) continue
        visitList[i] = true
        backTracking(i + 1, sumDiet + arr[i], "$sumValue ${i + 1}", targetDiet, visitList, arr)
        visitList[i] = false
    }

}