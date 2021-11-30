import java.util.*

class Baekjoon_1068 {
}

/** https://www.acmicpc.net/problem/1068 */
/* 참고 https://haeng-on.tistory.com/65 */
fun main() {
    val scan = Scanner(System.`in`)
    val n = scan.nextInt()

    val inputs = mutableListOf<Pair<Int, Int>>()
    for (i in 0 until n) {
        inputs.add(i to scan.nextInt())
    }

    var root: Node? = null
    inputs.sortedBy { it.second }
        .forEach {
            val index = it.first
            val parent = it.second

            if (parent == -1) {
                root = Node(index)
            } else {
                root?.setChildNode(parent, Node(index))
            }
        }

    val removeNodeValue = scan.nextInt()
    if(removeNodeValue == root?.value){
        println(0)
    } else {
        root?.removeNode(removeNodeValue)
        println(root?.searchleafNode())
    }
}

data class Node(val value: Int, val childs: MutableList<Node> = mutableListOf()) {

    fun setChildNode(parentValue: Int, childNode: Node) {
        if (value == parentValue) {
            childs.add(childNode)
        } else {
            childs.forEach { child ->
                child.setChildNode(parentValue, childNode)
            }
        }
    }

    fun removeNode(removeValue: Int) {
        val childsIter = childs.iterator()

        while (childsIter.hasNext()) {
            val child = childsIter.next()
            if(child.value == removeValue) {
                childsIter.remove()
            } else {
                child.removeNode(removeValue)
            }
        }
    }

    fun searchleafNode() : Int{
        return if(childs.isEmpty()) 1 else childs.sumBy { it.searchleafNode() }
    }
}