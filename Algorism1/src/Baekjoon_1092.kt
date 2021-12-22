import java.util.*

/** https://www.acmicpc.net/problem/1092 */

fun main() {
    val scan = Scanner(System.`in`)

    val craneNumber = scan.nextInt()
    val craneArr = mutableListOf<Int>()

    for (i in 0 until craneNumber) {
        craneArr.add(scan.nextInt())
    }

    val boxNumber = scan.nextInt()
    val boxArr = mutableListOf<Int>()
    for (i in 0 until boxNumber) {
        boxArr.add(scan.nextInt())
    }

    craneArr.sortDescending()
    boxArr.sortDescending()

    if (boxArr.first() > craneArr.first()) {
        println(-1)
        return
    }

    var boxIter = boxArr.iterator()

    var cnt = 0
    while (boxIter.hasNext()) {
        craneArr.forEach { crane ->
            if (!boxIter.hasNext()) return@forEach
            if (crane >= boxArr.last()) {
                while (boxIter.hasNext()) {
                    val box = boxIter.next()
                    if (box <= crane) {
                        boxArr.remove(box)
                        boxIter = boxArr.iterator()
                        break
                    }
                }
            }
        }
        cnt++
    }
    println(cnt)
}
