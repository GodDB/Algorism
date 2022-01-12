import MergeSort.mergeSort

object MergeSort {

    fun MutableList<Int>.mergeSort() {
        mergeSortInner(this, 0, this.size-1)
    }

    private fun mergeSortInner(list : MutableList<Int>, start : Int, end : Int) {
        if(start >= end) return

        val mid = (start + end)/2

        // 전부다 분할한다.
        mergeSortInner(list, start, mid)
        mergeSortInner(list, mid+1, end)

        // 합친다
        merge(list, start, end, mid)
    }

    /** mid를 기준으로 좌우로 나눠져 있는 값들을 정렬한다. */
    private fun merge(list: MutableList<Int>, start: Int, end: Int, mid: Int) {
        var left_start_index = start // left의 시작 인덱스
        val left_end_index = mid // left의 끝 인덱스
        var right_start_index = mid+1 // right의 시작 인덱스
        val right_end_index = end // right의 끝 인덱스

        val tempList = IntArray(list.size)
        var tempListIndex = start

        while ( left_start_index <= left_end_index && right_start_index <= right_end_index) {
            if(list[left_start_index] <= list[right_start_index]) {
                tempList[tempListIndex++] = list[left_start_index++]
            } else {
                tempList[tempListIndex++] = list[right_start_index++]
            }
        }

        //여기에 왔다는것은 left든 right든 둘 중 하나 / 둘다 비워졌다는 것이므로 잔반털이한다.
        while (left_start_index <= left_end_index) tempList[tempListIndex++] = list[left_start_index++]
        while (right_start_index <= right_end_index) tempList[tempListIndex++] = list[right_start_index++]

        //tempList에 저장한 걸 모조리 털어넣는다.
        for(i in start .. end) {
            list[i] = tempList[i]
        }
    }
}

fun main() {
    val list = mutableListOf<Int>(17,1, 4, 6, 7, 9, 2, 3, 5, 7, 3, 1)
    list.mergeSort()

    println(list)
}