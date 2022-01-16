import SelectionSort.insertionSort

object SelectionSort {

    fun MutableList<Int>.insertionSort() {
        for(i in 1 until this.size) {
            var j = i-1
            val key = this[i]
            //기준이 더 작을 때만 순회한다. 왼쪽은 정렬되어 있다
            while (j >= 0 && key < this[j]) {
                this[j+1] = this[j]
                j--
            }
            //한 칸 더 마이너스 되므로 +1
            this[j+1] = key
        }
    }
}

fun main() {

    val list = mutableListOf<Int>(9,8,4,3,3,5,1,1)
    list.insertionSort()
    println(list)
}