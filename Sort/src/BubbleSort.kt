import BubbleSort.bubbleSort

object BubbleSort {

    fun MutableList<Int>.bubbleSort() {
        for (i in 0 until this.size - 1) {
            for (j in 0 until this.size - 1) {
                if (this[j] > this[j + 1]) {
                   this.swap(j, j+1)
                }
            }
        }
    }

    private fun MutableList<Int>.swap(fromIndex: Int, toIndex: Int) {
        val temp = this[toIndex]
        this[toIndex] = this[fromIndex]
        this[fromIndex] = temp
    }
}


fun main() {

    val list = mutableListOf<Int>(5,3,8,1,2,7)
    list.bubbleSort()
    println(list)
}