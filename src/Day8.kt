import Day8.Part1.DSU
import Day8.Part1.Pos
import java.io.FileReader
import java.util.*
import kotlin.math.absoluteValue

class Day8 {
    companion object {
        fun fin(sample: Boolean) = Scanner(FileReader(if (sample) "sample.txt" else "input.txt"))
    }

    class Part1(sample: Boolean) {
        val fin = fin(sample)

        class DSU(n: Int) {
            val parent = IntArray(n) { it }

            fun find(x: Int): Int {
                if (parent[x] == x) return x
                parent[x] = find(parent[x])
                return parent[x]
            }

            fun unite(x: Int, y: Int): Boolean {
                val i = find(x)
                val j = find(y)
                if (i == j) return false
                parent[i] = parent[j]
                return true
            }
        }

        data class Pos(val i: Int, val x: Int, val y: Int, val z: Int) {
            fun dist(p: Pos): Long {
                val dx = (p.x - x.toLong()).absoluteValue
                val dy = (p.y - y.toLong()).absoluteValue
                val dz = (p.z - z.toLong()).absoluteValue
                return dx * dx + dy * dy + dz * dz
            }
        }

        fun solve() {
            val points = mutableListOf<Pos>()
            var index = 0
            while (fin.hasNext()) {
                val p = fin.next().split(',').map { it.toInt() }.let { Pos(index++, it[0], it[1], it[2]) }
                points.add(p)
            }
            val n = points.size
            val g = Array(n) { hashSetOf<Int>() }
            val dsu = DSU(n)
            repeat(1000) {
                var minDist = Long.MAX_VALUE
                var minDistI = -1
                var minDistJ = -1
                for (i in 0 until n) {
                    for (j in i + 1 until n) {
                        if (g[i].contains(j)) continue
                        val dist = points[i].dist(points[j])
                        if (dist < minDist) {
                            minDist = dist
                            minDistI = i
                            minDistJ = j
                        }
                    }
                }
                g[minDistI].add(minDistJ)
                g[minDistJ].add(minDistI)
                dsu.unite(minDistI, minDistJ)
                println("Connecting ${points[minDistI]} and ${points[minDistJ]}")
            }

            val used = hashSetOf<Int>()
            fun dfs(p: Int): Int {
                if (!used.add(p)) return 0
                return 1 + g[p].sumOf { dfs(it) }
            }

            val circ = mutableListOf<Int>()
            for (p in 0 until n) {
                if (p !in used) {
                    val sz = dfs(p)
                    circ.add(sz)
                }
            }
            circ.sortDescending()
            val ans = circ[0].toLong() * circ[1] * circ[2]
            println(ans)
        }
    }

    class Part2(sample: Boolean) {
        val fin = fin(sample)
        fun solve() {
            val points = mutableListOf<Pos>()
            var index = 0
            while (fin.hasNext()) {
                val p = fin.next().split(',').map { it.toInt() }.let { Pos(index++, it[0], it[1], it[2]) }
                points.add(p)
            }
            val n = points.size
            val g = Array(n) { hashSetOf<Int>() }
            val dsu = DSU(n)
            var timesDone = 0
            var ans = 0L
            while (timesDone < n - 1) {
                var minDist = Long.MAX_VALUE
                var minDistI = -1
                var minDistJ = -1
                for (i in 0 until n) {
                    for (j in i + 1 until n) {
                        if (g[i].contains(j)) continue
                        val dist = points[i].dist(points[j])
                        if (dist < minDist) {
                            minDist = dist
                            minDistI = i
                            minDistJ = j
                        }
                    }
                }
                g[minDistI].add(minDistJ)
                g[minDistJ].add(minDistI)
                ans = points[minDistI].x.toLong() * points[minDistJ].x.toLong()
                if (dsu.unite(minDistI, minDistJ)) timesDone++
            }

            println(ans)
        }
    }

}

fun main() {
//    Day8.Part1(sample = true).solve()
    Day8.Part1(sample = false).solve()
    Day8.Part2(sample = true).solve()
    Day8.Part2(sample = false).solve()
}
