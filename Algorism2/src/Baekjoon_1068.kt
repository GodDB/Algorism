import java.util.*

class Baekjoon_1068 {
}

/** https://www.acmicpc.net/problem/1068 */
fun main() {
    val scan = Scanner(System.`in`)
    val n = scan.nextInt()
    var rootValue = 0
    val nodes = mutableListOf<Node>()
    for (i in 0 until n) {
        val input = scan.nextInt()
        nodes.add(Node(i, input))
        if (input == -1) rootValue = i
    }
    val removeNodeValue = scan.nextInt()
    if (removeNodeValue == rootValue) {
        println(0)
        return
    } else {
        setupNodes(nodes)
        nodes[rootValue].removeNode(removeNodeValue)
        println(nodes[rootValue].searchleafNode())
    }
}

private fun setupNodes(nodes : List<Node>) {
    nodes.forEachIndexed { index, node ->
        if (!node.isRoot()) {
            nodes[node.parent].setChildNode(node.parent, node)
        }
    }
}

data class Node(val value: Int, val parent: Int, val childs: MutableList<Node> = mutableListOf()) {

    fun isRoot() = (parent == -1)

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
            if (child.value == removeValue) {
                childsIter.remove()
            } else {
                child.removeNode(removeValue)
            }
        }
    }

    fun searchleafNode(): Int {
        return if (childs.isEmpty()) 1 else childs.sumBy { it.searchleafNode() }
    }
}