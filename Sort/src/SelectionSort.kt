import SelectionSort.selectionSort

object SelectionSort {

    fun MutableList<Int>.selectionSort() {
        for(i in 0 until this.size) {
            var minValue = i to Int.MAX_VALUE
            for(j in i until this.size) {
                minValue = if(minValue.second > this[j]) j to this[j] else minValue
            }
            this.swap(i, minValue.first)
        }
    }

    private fun MutableList<Int>.swap(fromIndex: Int, toIndex: Int) {
        val temp = this[toIndex]
        this[toIndex] = this[fromIndex]
        this[fromIndex] = temp
    }

}

fun main() {
    val list = mutableListOf<Int>(9, 8, 4, 3, 3, 5, 1, 1)
    list.selectionSort()
    println(list)
}