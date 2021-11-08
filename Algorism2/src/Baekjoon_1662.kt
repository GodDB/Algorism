import java.util.*


/** https://www.acmicpc.net/problem/1662 */

fun main() {

    val scan = Scanner(System.`in`)

    val valueList = scan.next().split("").filter { it != "" && it != " "}
    val stack = Stack<String>()
    for(value in valueList) {
        //닫는 괄호 나올때까지 stack push
        if(value != ")") {
            stack.push(value)
        }
        //닫는 괄호가 나오면 여는 괄호가 나올때까지 stack pop후 카운트 더함
        //그 와중에 *이 있으면 그 값을 더함... 이전에 계산완료된 값임
        else {
            var count = 0
            while (stack.isNotEmpty() && stack.peek() != "(") {
                if(stack.pop() == "*") count += stack.pop().toInt() else count++
            }

            // (를 날려버리고 숫자값을 곱해서 다시 *표시를 해준다.
            stack.pop()
            stack.push((count * stack.pop().toInt()).toString())
            stack.push("*")
        }
    }

    // *표시가 있는것은 계산 완료된 값이므로 그 값 자체를 더하고 없다면 갯수만 더한다.
    var result = 0
    while (stack.isNotEmpty()) {
        if(stack.pop() == "*") result += stack.pop().toInt() else result++
    }

    println(result)
}