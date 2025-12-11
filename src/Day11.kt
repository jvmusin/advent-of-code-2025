import java.io.FileReader
import java.util.*

class Day11 {
    companion object {
        fun fin(sample: Boolean) = Scanner(FileReader(if (sample) "sample.txt" else "input.txt"))
    }

    class Part1(sample: Boolean) {
        val fin = fin(sample)

        fun solve() {
            val g = hashMapOf<String, List<String>>()
            while (fin.hasNextLine()) {
                val s = fin.nextLine()
                val (from, toAll) = s.split(": ")
                g[from] = toAll.split(' ')
            }
            val dp = hashMapOf<String, Long>()
            fun dfs(cur: String): Long {
                if (cur == "out") return 1
                dp[cur]?.let { return it }

                val res = g[cur]?.sumOf { dfs(it) } ?: 0
                dp[cur] = res
                return res
            }
            println(dfs("you"))
        }
    }

    class Part2(sample: Boolean) {
        val fin = fin(sample)

        fun solve() {
            val g = hashMapOf<String, List<String>>()
            while (fin.hasNextLine()) {
                val s = fin.nextLine()
                val (from, toAll) = s.split(": ")
                g[from] = toAll.split(' ')
            }
            data class State(val v: String, val cnt: Int)

            val dp = hashMapOf<State, Long>()
            fun dfs(cur: String, curCnt: Int): Long {
                if (cur == "out") return if (curCnt == 2) 1 else 0
                val state = State(cur, curCnt)
                dp[state]?.let { return it }

                var cnt = curCnt
                if (cur == "dac" || cur == "fft")
                    cnt++

                val res = g[cur]!!.sumOf { dfs(it, cnt) }
                dp[state] = res
                return res
            }
            println(dfs("svr", 0))
        }
    }
}

fun main() {
    Day11.Part1(sample = true).solve()
    Day11.Part1(sample = false).solve()
    Day11.Part2(sample = true).solve()
    Day11.Part2(sample = false).solve()
}
