import java.util.*

/** https://www.acmicpc.net/problem/2800 */

data class Bracket(val start : Int, val end : Int)

fun main() {

    val scan = Scanner(System.`in`)
    val stack = Stack<Int>()
    val bracketList = mutableListOf<Bracket>()
    val result = TreeSet<String>() // 중복이 발생할 수 있고, 사전 순으로 정렬하기 위해 TreeSet 사용
    val inputList = scan.next().split("").filter { it != "" && it != " " }.toMutableList()

    // 괄호 쌍의 인덱스를 모델링하여 bracketList를 구성한다.
    inputList.forEachIndexed { index, value ->
        if(value == "(") {
            stack.push(index)
        } else if (value == ")") {
            bracketList.add(Bracket(start = stack.pop(), end = index))
        }
    }

    backTracking(0, inputList, bracketList, result)

    // 안없앤 상태에서 한번 했기 때문에 아무 괄호도 제거되지 않은게 포함된다. 사전순으로 정의하여 0번째에 무조건 자리잡기 때문에 0번째 빼고 출력
    result.forEachIndexed { index, s ->
        if(index != 0) println(s)
    }
}

fun backTracking(index : Int, inputList : MutableList<String>, bracketList : List<Bracket>, result : TreeSet<String>) {
    if(index == bracketList.size) {
        result.add(inputList.joinToString(""))
        return
    }

    for(i in index until bracketList.size) {
        val bracket = bracketList[i]

        backTracking(i+1, inputList, bracketList, result) // 이번 인덱스에선 안 없앤 상태로 한번
        inputList[bracket.start] = ""
        inputList[bracket.end] = ""
        backTracking(i+1, inputList, bracketList, result) // 없앤 상태로 한번
        inputList[bracket.start] = "("
        inputList[bracket.end] = ")"
    }

}