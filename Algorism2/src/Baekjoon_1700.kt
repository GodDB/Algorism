import java.util.*
import kotlin.NoSuchElementException

/** https://www.acmicpc.net/problem/1700 */

fun main() {
    val scan = Scanner(System.`in`)

    val n = scan.nextInt()
    val m = scan.nextInt()

    val set = mutableSetOf<Int>()
    val arr = mutableListOf<Int>()

    repeat(m) {
        arr.add(scan.nextInt())
    }

    var answer = 0

    for (i in 0 until arr.size) {
        //사이즈가 덜 찼다면 그냥 넣는다.
        if (set.size < n) {
            set.add(arr[i])
            continue
        }

        //사이즈가 찼는데 그게 셋안에 있다면 패스한다.
        if (set.contains(arr[i])) continue

        // 사이즈가 찼는데 그게 셋 안에 없다면 쓸모없는 수를 제거하고 새로운 수를 set에 담는다.
        answer++
        val item = findRemoveItem(set, arr, i, n)
        set.remove(item)
        set.add(arr[i])
    }
    println(answer)
}

// 리스트 안에서 현재 가장 쓸모없는 수를 찾아, 셋에서 제거한다.
// 쓸모 없다의 기준 - set에 있으나 list내에 없다, set에 있고 list에 있으나, 우선순위가 가장 낮다.
private fun findRemoveItem(set: Set<Int>, arr: List<Int>, startIndex: Int, n: Int): Int {
    // 앞으로 들어올 list item이 set에 없다면
    set.forEach { setValue ->
        if (!arr.isContain(startIndex until arr.size, setValue)) return setValue
    }

    val checkSet = mutableSetOf<Int>()
    // 모두 set에 있지만 우선순위가 낮다
    for (i in startIndex until arr.size) {
        if (!set.contains(arr[i])) continue
        if (checkSet.contains(arr[i])) continue
        if (checkSet.size == n - 1) return arr[i]
        checkSet.add(arr[i])
    }

    //발생할 수 없음 ..
    throw NoSuchElementException("not found item")
}

private inline fun <T> List<T>.isContain(range: IntRange, targetValue: T): Boolean {
    for (i in range) {
        if (this[i] == targetValue) {
            return true
        }
    }
    return false
}
