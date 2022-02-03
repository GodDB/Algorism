import java.util.*

/** https://www.acmicpc.net/problem/10775 */
fun main() {
    val scan = Scanner(System.`in`)

    val n = scan.nextInt()
    val m = scan.nextInt()

    val unionFindList = UnionFindDecorator(MutableList(n + 1) { it })

    var answer = 0

    run {
        repeat(m) {
            val input = scan.nextInt()

            val inputParent = unionFindList.findParent(input)

            if (inputParent == 0) return@run

            unionFindList.union(inputParent, inputParent - 1)
            answer++
        }
    }
    println(answer)

}


class UnionFindDecorator(private val inner: MutableList<Int>) : MutableList<Int> by inner {

    fun findParent(value: Int): Int {
        if (inner[value] == value) return value

        val newValue = findParent(inner[value])
        inner[value] = newValue
        return inner[value]
    }

    fun union(value1: Int, value2: Int) {
        val value1Parent = findParent(value1)
        val value2Parent = findParent(value2)

        if (value1Parent != value2Parent) inner[value1Parent] = value2Parent
    }

    override fun toString(): String {
        return inner.toString()
    }
}