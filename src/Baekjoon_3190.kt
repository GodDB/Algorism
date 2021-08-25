import java.util.*

/** https://www.acmicpc.net/problem/3190 */

data class Point(val x : Int, val y : Int)

fun main() {
    val scan = Scanner(System.`in`)

    val n = scan.nextInt()
    val k = scan.nextInt()

    val appleSpot : MutableList<MutableList<Boolean>> = MutableList(n+1) { MutableList(n+1) { false } }
    val turnTime : MutableList<Pair<Int, String>> = mutableListOf()

    for( i in 0 until k) {
        val x = scan.nextInt()
        val y = scan.nextInt()
        appleSpot[y][x] = true
    }

    val k2 = scan.nextInt()
    for( i in 0 until k2) {
        turnTime.add(scan.nextInt() to scan.next())
    }

    startGame(appleSpot, turnTime, n)
}

private var currentDirection : Int = 1

private fun changeDirection(direction : String) {
    when(direction) {
        "L" -> {
            currentDirection--
            if(currentDirection < 0) currentDirection = 3
        }
        "D" -> {
            currentDirection++
            if(currentDirection > 3) currentDirection = 0
        }
    }
}


private fun startGame(gameBoard : MutableList<MutableList<Boolean>>, turnTime : List<Pair<Int, String>>, n : Int) {
    var totalTime = 0
    val cx = listOf<Int>(0, 1, 0, -1)
    val cy = listOf<Int>(-1, 0, 1, 0)
    val deque : Deque<Point> = LinkedList<Point>().apply {
        offerFirst(Point(1,1))
    }

    while(true){
        //방향 전환할 시간이 되었는가?
        turnTime.forEach {  time ->
            if(time.first == totalTime) changeDirection(time.second)
        }

        totalTime ++
        val headPos = deque.peekLast()
        val newHeadPosX = headPos.x + cx[currentDirection]
        val newHeadPosY = headPos.y + cy[currentDirection]

        // 게임판에서 벗어났는가?
        if(newHeadPosX < 1 || newHeadPosX > n || newHeadPosY < 1 || newHeadPosY > n) return println("${totalTime}")

        // 꼬리에 부딪혔는가?
        deque.forEachIndexed { index, oldPos ->
            if(index == deque.size-1) return@forEachIndexed
            if(newHeadPosX == oldPos.x && newHeadPosY == oldPos.y) return println("${totalTime}")
        }

        //사과를 먹었는가?
        if(gameBoard[newHeadPosX][newHeadPosY]) {
            gameBoard[newHeadPosX][newHeadPosY] = false //사과 제거
            deque.offerLast(Point(newHeadPosX, newHeadPosY))
        } else {
            deque.run {
                pollFirst()
                offerLast(Point(newHeadPosX, newHeadPosY))
            }
        }

    }
}

