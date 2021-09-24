import java.util.*

/** https://www.acmicpc.net/problem/12904 */

fun main() {
    val scan = Scanner(System.`in`)

    val value = scan.next()
    val targetValue = scan.next()

    // 반대로 targetValue를 기준으로 입력받은 값으로 만들 수 있는지로 로직을 진행한다.
    var newTargetValue = value
    var newCurrentValue = targetValue

    while (newCurrentValue.length > newTargetValue.length) {
        newCurrentValue = if (newCurrentValue.last() == 'A') {
            //A
            removeLast(newCurrentValue)
        } else {
            //B
            removeLastAndReverse(newCurrentValue)
        }
    }
    if(newCurrentValue == newTargetValue){
        println("1")
    }else {
        println("0")
    }
}

private fun removeLastAndReverse(str: String): String {
    return removeLast(str).reversed()
}

private fun removeLast(str: String): String {
    return str.slice(0 until str.length - 1)
}