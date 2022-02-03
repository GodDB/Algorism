import UnionFind.Companion.asUnionFind

/**  Union Find를 구현한다.
 *
 * Union Find는 합집합을 구하기 위한 자료구조이다.
 */

class UnionFind private constructor(private val inner: MutableList<Int>) : MutableList<Int> by inner {

    companion object {
        fun MutableList<Int>.asUnionFind() : UnionFind {
            return UnionFind(this)
        }
    }

    fun findParent(value : Int) : Int {
        if(inner[value] == value) return inner[value]

        return inner.set(value, findParent(inner[value]))
    }

    fun unionTo(_value1 : Int, _value2 : Int) {
        val value1 = findParent(_value1)
        val value2 = findParent(_value2)

        if(value1 > value2) {
            inner[value1] = value2
        } else {
            inner[value2] = value1
        }
    }

    override fun toString(): String {
        return inner.toString()
    }
}

fun main() {

    val list = mutableListOf<Int>(0,1,2,3,4,5,6,7,8)

    val union = list.asUnionFind()

    println(union.findParent(3))

    union.unionTo(0, 1)
    println(union.toString())

    union.unionTo(1, 2)
    println(union.toString())

    union.unionTo(1, 3)
    println(union.toString())

    union.unionTo(3, 6)
    println(union.toString())


}