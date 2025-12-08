import java.io.FileReader
import java.util.*

class Day7 {
    companion object {
        fun fin(sample: Boolean) = Scanner(FileReader(if (sample) "sample.txt" else "input.txt"))
    }

    class Part1(sample: Boolean) {
        val fin = fin(sample)
        fun solve() {
            val field = mutableListOf<String>()
            while (fin.hasNextLine()) field.add(fin.nextLine())

            fun inside(x: Int, y: Int) = x in field.indices && y in field[x].indices

            data class IntPair(val x: Int, val y: Int)

            var active = hashSetOf<IntPair>()
            active.add(IntPair(0, field[0].indexOf('S')))
            var splits = 0
            while (active.any()) {
                val nextActive = hashSetOf<IntPair>()
                for ((x, y) in active) {
                    if (!inside(x + 1, y)) continue
                    if (field[x + 1][y] == '.') {
                        nextActive += IntPair(x + 1, y)
                        continue
                    }
                    splits++
                    if (inside(x + 1, y - 1)) nextActive += IntPair(x + 1, y - 1)
                    if (inside(x + 1, y + 1)) nextActive += IntPair(x + 1, y + 1)
                }
                active = nextActive
            }
            println(splits)
        }
    }

    class Part2(sample: Boolean) {
        val fin = fin(sample)
        fun solve() {
            val field = mutableListOf<String>()
            while (fin.hasNextLine()) field.add(fin.nextLine())

            fun inside(x: Int, y: Int) = x in field.indices && y in field[x].indices

            data class IntPair(val x: Int, val y: Int)

            val dp = hashMapOf<IntPair, Long>()

            fun getAns(x: Int, y: Int): Long {
                if (!inside(x + 1, y)) return 1L
                val state = IntPair(x, y)
                dp[state]?.let { return it }

                if (field[x + 1][y] == '.') {
                    val ans = getAns(x + 1, y)
                    dp[state] = ans
                    return ans
                }

                var curTotal = 0L
                if (inside(x + 1, y - 1)) curTotal += getAns(x + 1, y - 1)
                if (inside(x + 1, y + 1)) curTotal += getAns(x + 1, y + 1)

                dp[state] = curTotal
                return curTotal
            }

            println(getAns(0, field[0].indexOf('S')))
        }
    }

}

fun main() {
    Day7.Part1(sample = true).solve()
    Day7.Part1(sample = false).solve()
    Day7.Part2(sample = true).solve()
    Day7.Part2(sample = false).solve()
}
