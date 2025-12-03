import java.io.FileReader
import java.util.*
import kotlin.math.absoluteValue
import kotlin.math.sign

class Day1 {
    companion object {
        fun fin(sample: Boolean) = Scanner(FileReader(if (sample) "sample.txt" else "input.txt"))
    }

    class Part1(sample: Boolean) {
        val fin = fin(sample)
        fun solve() {
            fun toNumber(s: String): Int {
                val sign = if (s[0] == 'L') -1 else +1
                val value = s.drop(1).toInt()
                return sign * value
            }

            val ans = generateSequence { fin.next() }
                .takeWhile { fin.hasNext() }
                .map(::toNumber)
                .scan(50, Int::plus)
                .count { it % 100 == 0 }
            println(ans)
        }
    }

    class Part2(sample: Boolean) {
        val fin = fin(sample)
        fun solve() {
            fun toNumber(s: String): Int {
                val sign = if (s[0] == 'L') -1 else +1
                val value = s.drop(1).toInt()
                return sign * value
            }

            fun toList(n: Int): List<Int> {
                return List(n.absoluteValue) { n.sign }
            }

            val ans = generateSequence { fin.next() }
                .takeWhile { fin.hasNext() }
                .map(::toNumber)
                .flatMap(::toList)
                .scan(50, Int::plus)
                .count { it % 100 == 0 }
            println(ans)
        }
    }
}

fun main() {
    Day1.Part1(sample = true).solve()
    Day1.Part1(sample = false).solve()
    Day1.Part2(sample = true).solve()
    Day1.Part2(sample = false).solve()
}
