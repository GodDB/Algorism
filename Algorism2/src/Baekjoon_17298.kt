import java.util.*

fun main() {
    val scan = Scanner(System.`in`)

    val tempArr = mutableListOf<Int>()
    val stack = Stack<Int>()
    val n = scan.nextInt()

    for(i in 0 until n) {
        tempArr.add(scan.nextInt())
    }


    for(i in 0 until n) {
        val targetValue = tempArr[i]

        if(stack.isEmpty()){
            stack.push(i)
        }
        else {
            while (stack.isNotEmpty()) {
                val valueIndex = stack.peek()

                if(targetValue <= tempArr[valueIndex]) {
                    stack.push(i)
                    break
                } else {
                    tempArr[valueIndex] = targetValue
                    stack.pop()

                    if(stack.isEmpty()){
                        stack.push(i)
                        break
                    }
                }
            }
        }
    }

    stack.forEach {
        tempArr[it] = -1
    }
    println(tempArr.joinToString(" "))
}
