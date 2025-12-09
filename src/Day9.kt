import java.io.FileReader
import java.util.*
import kotlin.math.abs
import kotlin.math.absoluteValue

class Day9 {
    companion object {
        fun fin(sample: Boolean) = Scanner(FileReader(if (sample) "sample.txt" else "input.txt"))
    }

    class Part1(sample: Boolean) {
        val fin = fin(sample)

        data class IntPair(val x: Int, val y: Int)

        fun solve() {
            val dots = mutableListOf<IntPair>()
            while (fin.hasNextLine()) {
                val (c, r) = fin.nextLine().split(',').map { it.toInt() }
                dots.add(IntPair(c, r))
            }
            var maxSize = 0L
            for (i in dots.indices) {
                for (j in 1 until dots.size) {
                    val p1 = dots[i]
                    val p2 = dots[j]
                    val dx = abs(p1.x - p2.x)
                    val dy = abs(p1.y - p2.y)
                    val size = (dx + 1L) * (dy + 1L)
                    maxSize = maxOf(maxSize, size)
                }
            }
            println(maxSize)
        }
    }

    class Part2(sample: Boolean) {
        val fin = fin(sample)

        data class IntPair(val r: Int, val c: Int)

        fun solve() {
            val allPositions = hashSetOf<Int>()
            val dots = mutableListOf<IntPair>()
            while (fin.hasNextLine()) {
                val (c, r) = fin.nextLine().split(',').map { it.toInt() * 2 }
                dots.add(IntPair(r, c))
                for (d in -1..1) {
                    allPositions.add(c + d)
                    allPositions.add(r + d)
                }
            }
            val indexToPosition = allPositions.sorted()
            val positionToIndex = indexToPosition.mapIndexed { i, x -> x to i }.toMap()

            val n = allPositions.size
            val field = Array(n) { IntArray(n) }
            for (i in 0 until dots.size) {
                val p1 = dots[i]
                val p2 = dots[(i + 1) % dots.size]
                val r1 = positionToIndex[p1.r]!!
                val r2 = positionToIndex[p2.r]!!
                val c1 = positionToIndex[p1.c]!!
                val c2 = positionToIndex[p2.c]!!
                for (r in minOf(r1, r2)..maxOf(r1, r2))
                    for (c in minOf(c1, c2)..maxOf(c1, c2))
                        field[r][c] = 1
            }

            val q = ArrayDeque<IntPair>()
            q.add(IntPair(0, 0))
            while (q.isNotEmpty()) {
                val (r, c) = q.poll()
                if (r !in 0 until n || c !in 0 until n || field[r][c] != 0) continue
                field[r][c] = 2
                q.add(IntPair(r + 1, c))
                q.add(IntPair(r - 1, c))
                q.add(IntPair(r, c + 1))
                q.add(IntPair(r, c - 1))
            }

            var ans = 0L
            for (i in dots.indices) {
                val p1 = dots[i]
                val r1 = positionToIndex[p1.r]!!
                val c1 = positionToIndex[p1.c]!!
                for (j in i + 1 until dots.size) {
                    val p2 = dots[j]
                    val r2 = positionToIndex[p2.r]!!
                    val c2 = positionToIndex[p2.c]!!
                    var anyTwo = false
                    for (r in minOf(r1, r2)..maxOf(r1, r2)) {
                        for (c in minOf(c1, c2)..maxOf(c1, c2)) {
                            if (field[r][c] == 2) {
                                anyTwo = true
                            }
                        }
                    }
                    if (anyTwo) continue
                    val dr = (p1.r / 2 - p2.r / 2).absoluteValue + 1L
                    val dc = (p1.c / 2 - p2.c / 2).absoluteValue + 1L
                    ans = maxOf(ans, dr * dc)
                }
            }
            println(ans)
        }
    }
}

fun main() {
    Day9.Part1(sample = true).solve()
    Day9.Part1(sample = false).solve()
    Day9.Part2(sample = true).solve()
    Day9.Part2(sample = false).solve()
}
