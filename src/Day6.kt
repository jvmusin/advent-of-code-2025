import java.io.FileReader
import java.util.*

class Day6 {
    companion object {
        fun fin(sample: Boolean) = Scanner(FileReader(if (sample) "sample.txt" else "input.txt"))
    }

    class Part1(sample: Boolean) {
        val fin = fin(sample)
        fun solve() {
            val lines = mutableListOf<List<String>>()
            while (fin.hasNextLine()) {
                val line = fin.nextLine().trim().split(Regex(" +"))
                lines += line
            }

            var ans = 0L
            for (i in 0 until lines.last().size) {
                val sign = lines.last()[i]
                var base = if (sign == "+") 0L else 1L
                for (j in 0 until lines.size - 1) {
                    if (sign == "+") base += lines[j][i].toLong()
                    else base *= lines[j][i].toLong()
                }
                ans += base
            }
            println(ans)
        }
    }

    class Part2(sample: Boolean) {
        val fin = fin(sample)
        fun solve() {
            val lines = mutableListOf<String>()
            while (fin.hasNextLine()) {
                val line = fin.nextLine()
                lines += line
            }
            var col = 0
            var ans = 0L
            while (col < lines.maxOf { it.length }) {
                var any = true
                val firstCol = col
                val numbersStrings = mutableListOf<String>()

                while (any) {
                    var curNumber = ""
                    any = false
                    for (i in 0 until lines.size - 1) {
                        if (col < lines[i].length && lines[i][col] != ' ') {
                            any = true
                            curNumber += lines[i][col]
                        }
                    }
                    if (any) numbersStrings += curNumber
                    col++
                }

                val sign = lines.last()[firstCol]
                var base = if (sign == '+') 0L else 1L
                for (s in numbersStrings) {
                    val x = s.toLong()
                    if (sign == '+') base += x
                    else base *= x
                }
                ans += base
            }
            println(ans)
        }
    }

}

fun main() {
    Day6.Part1(sample = true).solve()
    Day6.Part1(sample = false).solve()
    Day6.Part2(sample = true).solve()
    Day6.Part2(sample = false).solve()
}
