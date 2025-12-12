import java.io.FileReader
import java.util.*

class Day12 {
    companion object {
        fun fin(sample: Boolean) = Scanner(FileReader(if (sample) "sample.txt" else "input.txt"))
    }

    class Part1(sample: Boolean) {
        val fin = fin(sample)

        companion object {
            fun rotateRight(a: Array<BooleanArray>): Array<BooleanArray> {
                val n = a.size
                val m = a[0].size
                return Array(m) { j -> BooleanArray(n) { i -> a[n - 1 - i][j] } }
            }

            fun rotateRight(a: Array<BooleanArray>, times: Int): Array<BooleanArray> {
                var res = a
                repeat(times) { res = rotateRight(res) }
                return res
            }
        }

        data class Present(val n: Int, val m: Int, val field: Array<BooleanArray>) {
            val rotates = Array(4) { rotateRight(field, it) }
        }

        data class Query(val n: Int, val m: Int, val cnt: IntArray)

        class Checker(var presents: List<Present>, var query: Query) {
            val field = Array(query.n) { BooleanArray(query.m) }
            val rem = query.cnt.copyOf()

            fun canFit(p: Int, rot: Int, i0: Int, j0: Int, apply: Boolean): Boolean {
                val shape = presents[p].rotates[rot]
                val n = shape.size
                val m = shape[0].size
                if (i0 + n > field.size || j0 + m > field[0].size) return false
                for (i in 0 until n) {
                    for (j in 0 until m) {
                        if (shape[i][j]) {
                            if (apply) {
                                field[i0 + i][j0 + j] = !field[i0 + i][j0 + j]
                            } else {
                                if (field[i0 + i][j0 + j]) {
                                    return false
                                }
                            }
                        }
                    }
                }
                return true
            }

            fun check(p: Int): Boolean {
                if (p == rem.size) return true
                if (rem[p] == 0) return check(p + 1)

                repeat(field.size) { i0 ->
                    repeat(field[i0].size) { j0 ->
                        repeat(4) { rot ->
                            if (canFit(p, rot, i0, j0, false)) {
                                canFit(p, rot, i0, j0, true)
                                rem[p]--
                                if (check(p)) return true
                                rem[p]++
                            }
                        }
                    }
                }
                return false
            }
        }

        fun solve() {
            val fullInput = mutableListOf<String>()
            while (fin.hasNextLine()) {
                fullInput += fin.nextLine()
            }
            val emptyLines = fullInput.indices.filter { fullInput[it].isEmpty() }
            val presents = mutableListOf<Present>()
            for (i in emptyLines.indices) {
                val shape = fullInput.subList(if (i == 0) 1 else emptyLines[i - 1] + 2, emptyLines[i])
                val n = shape.size
                val m = shape[0].length
                val bShape = Array(n) { i -> BooleanArray(m) { j -> shape[i][j] != '.' } }
                presents.add(Present(n, m, bShape))
            }

            val queries = fullInput.subList(emptyLines.last() + 1, fullInput.size).map { s ->
                val (size, cnts) = s.split(": ")
                val (n, m) = size.split('x').map { it.toInt() }
                val cnt = cnts.split(' ').map { it.toInt() }.toIntArray()
                Query(n, m, cnt)
            }

            println(queries.count { Checker(presents, it).check(0) })
        }
    }

    class Part2(sample: Boolean) {
        val fin = fin(sample)

        fun solve() {

        }
    }
}

fun main() {
    Day12.Part1(sample = true).solve()
    Day12.Part1(sample = false).solve()
    Day12.Part2(sample = true).solve()
    Day12.Part2(sample = false).solve()
}
