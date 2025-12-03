import java.io.FileReader
import java.util.*

class Day3 {
    companion object {
        fun fin(sample: Boolean) = Scanner(FileReader(if (sample) "sample.txt" else "input.txt"))
    }

    class Part1(sample: Boolean) {
        val fin = fin(sample)
        fun solve() {
            var ans = 0
            while (fin.hasNext()) {
                val s = fin.next()
                var maxDigit = s.last().digitToInt()
                var maxNumber = 0
                for (i in s.length - 2 downTo 0) {
                    val curDigit = s[i].digitToInt()
                    val curNumber = curDigit * 10 + maxDigit
                    maxNumber = maxOf(maxNumber, curNumber)
                    maxDigit = maxOf(maxDigit, curDigit)
                }
                ans += maxNumber
            }
            println(ans)
        }
    }

    class Part2(sample: Boolean) {
        val fin = fin(sample)
        fun solve() {
            var ans = 0L
            while (fin.hasNext()) {
                val s = fin.next()
                var buffer = s.takeLast(12)
                for (i in s.length - 13 downTo 0) {
                    val curDigit = s[i].digitToInt()
                    buffer = curDigit.toString() + buffer
                    for (j in 0 until buffer.length - 1) {
                        if (buffer[j] < buffer[j + 1]) {
                            buffer = buffer.take(j) + buffer.drop(j + 1)
                            break
                        }
                    }
                    if (buffer.length == 13) buffer = buffer.dropLast(1)
                }
                ans += buffer.toLong()
            }
            println(ans)
        }
    }
}

fun main() {
    Day3.Part1(sample = true).solve()
    Day3.Part1(sample = false).solve()
    Day3.Part2(sample = true).solve()
    Day3.Part2(sample = false).solve()
}
