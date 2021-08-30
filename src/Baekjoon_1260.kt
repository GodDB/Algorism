import java.util.*

lateinit var visitList : MutableList<Boolean>

fun main() {
    val scan = Scanner(System.`in`)

    val n = scan.nextInt()
    val m = scan.nextInt()
    val k = scan.nextInt()

    val arr: MutableList<MutableList<Int>> = MutableList(n + 1) { mutableListOf() }
    visitList = MutableList(n+1) { false }

    for (i in 0 until m) {
        val startNode = scan.nextInt()
        val endNode = scan.nextInt()

        arr[startNode].add(endNode)
        arr[endNode].add(startNode)
    }

    for (nodeList in arr) {
        nodeList.sortBy { it }
    }

    dfs(arr, k)
    println()
    bfs(arr, k)
}


private fun dfs(arr: List<List<Int>>, startIndex: Int) {
    visitList[startIndex] = true
    print("$startIndex ")

    for (node in arr[startIndex]) {
        if(!visitList[node]){
            dfs(arr, node)
        }
    }
}

private fun bfs(arr : List<List<Int>>, startIndex: Int) {

    val queue : Queue<Int> = LinkedList()
    queue.add(startIndex)
    print("$startIndex ")
    visitList[startIndex] = false

    while(queue.isNotEmpty()){
        val index = queue.poll()
        for( value in arr[index] ){
            if(visitList[value]) {
                visitList[value] = false
                print("$value ")
                queue.add(value)
            }
        }
    }
}