import QuickSort.quickSort

object QuickSort {

    fun MutableList<Int>.quickSort() {
        quickSortInner(this, 0, this.size-1)
    }

    private fun quickSortInner(list: MutableList<Int>, _start: Int, _end: Int) {
        if (_start >= _end) return

        var startIndex = _start
        var endIndex = _end

        val pivotIndex = startIndex //좌측 기준 퀵소트
        val pivot = list[pivotIndex]

        while (startIndex < endIndex) {
            // end value가 pivot 보다 클때까지 탐색한다.
            while (list[endIndex] > pivot) endIndex--
            // start value가 pivot 값보다 작을 때 까지 탐색한다.
            while (startIndex < endIndex && list[startIndex] <= pivot) startIndex++

            //두 탐색이 종료됬다면 swap한다.
            list.swap(startIndex, endIndex)
        }

        // start index >= end index 일 때, start index와 pivot값을 swap한다.
        list.swap(pivotIndex, startIndex)

        // 분할 정복을 시행한다.
        quickSortInner(list, _start, startIndex - 1)
        quickSortInner(list, startIndex + 1, _end)
    }

    private fun MutableList<Int>.swap(fromIndex: Int, toIndex: Int) {
        val temp = this[toIndex]
        this[toIndex] = this[fromIndex]
        this[fromIndex] = temp
    }
}

fun main() {
    val list = mutableListOf<Int>(1, 4, 6, 7, 9, 2, 3, 5, 7, 3, 1)
    list.quickSort()

    println(list)
}