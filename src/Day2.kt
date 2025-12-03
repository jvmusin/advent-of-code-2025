import java.io.FileReader
import java.util.*

class Day2 {
    companion object {
        fun fin(sample: Boolean) = Scanner(FileReader(if (sample) "sample.txt" else "input.txt"))
    }

    class Part1(sample: Boolean) {
        val fin = fin(sample)
        fun solve() {
            var ans = 0L
            val pairs = fin.next().split(',').map { it.split('-').map { it.toLong() } }
            for ((x, y) in pairs) {
                for (z in x..y) {
                    val str = z.toString()
                    if (str.length % 2 == 0 && str.take(str.length / 2) == str.drop(str.length / 2)) {
                        ans += z
                    }
                }
            }
            println(ans)
        }
    }

    class Part2(sample: Boolean) {
        val fin = fin(sample)
        fun solve() {
            fun isRepeated(n: Long): Boolean {
                val s = n.toString()
                for (subLen in 1 until s.length) {
                    if (s.length % subLen == 0) {
                        if ((s.indices step subLen).map { s.substring(it, it + subLen) }.toSet().size == 1) {
                            return true
                        }
                    }
                }
                return false
            }

            var ans = 0L
            val pairs = fin.next().split(',').map { it.split('-').map { it.toLong() } }
            for ((x, y) in pairs) {
                for (z in x..y) {
                    if (isRepeated(z)) {
                        ans += z
                    }
                }
            }
            println(ans)
        }
    }
}

fun main() {
    Day2.Part1(sample = true).solve()
    Day2.Part1(sample = false).solve()
    Day2.Part2(sample = true).solve()
    Day2.Part2(sample = false).solve()
}
