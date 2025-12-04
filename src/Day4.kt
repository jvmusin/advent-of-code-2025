import java.io.FileReader
import java.util.*

class Day4 {
    companion object {
        fun fin(sample: Boolean) = Scanner(FileReader(if (sample) "sample.txt" else "input.txt"))
    }

    class Part1(sample: Boolean) {
        val fin = fin(sample)
        fun solve() {
            val field = mutableListOf<String>()
            while (fin.hasNext()) field.add(fin.next())
            fun inside(x: Int, y: Int): Boolean {
                return x in 0 until field.size && y in 0 until field[x].length
            }

            fun getCount8(x: Int, y: Int, c: Char): Int {
                var count = 0
                for (i in -1..1) {
                    for (j in -1..1) {
                        if (i != 0 || j != 0) {
                            val x1 = x + i
                            val y1 = y + j
                            if (inside(x1, y1) && field[x1][y1] == c) {
                                count++
                            }
                        }
                    }
                }
                return count
            }

            var ans = 0
            for (i in field.indices) {
                val row = field[i]
                for (j in row.indices) {
                    if (row[j] == '@' && getCount8(i, j, '@') < 4) {
                        ans++
                    }
                }
            }
            println(ans)
        }
    }

    class Part2(sample: Boolean) {
        val fin = fin(sample)
        fun solve() {
            val field = mutableListOf<CharArray>()
            while (fin.hasNext()) field.add(fin.next().toCharArray())
            fun inside(x: Int, y: Int): Boolean {
                return x in 0 until field.size && y in 0 until field[x].size
            }

            fun getCount8(x: Int, y: Int, c: Char): Int {
                var count = 0
                for (i in -1..1) {
                    for (j in -1..1) {
                        if (i != 0 || j != 0) {
                            val x1 = x + i
                            val y1 = y + j
                            if (inside(x1, y1) && field[x1][y1] == c) {
                                count++
                            }
                        }
                    }
                }
                return count
            }

            var ans = 0
            var any = true
            while (any) {
                any = false
                for (i in field.indices) {
                    val row = field[i]
                    for (j in row.indices) {
                        if (row[j] == '@' && getCount8(i, j, '@') < 4) {
                            ans++
                            field[i][j] = '.'
                            any = true
                        }
                    }
                }
            }
            println(ans)
        }
    }
}

fun main() {
    Day4.Part1(sample = true).solve()
    Day4.Part1(sample = false).solve()
    Day4.Part2(sample = true).solve()
    Day4.Part2(sample = false).solve()
}
