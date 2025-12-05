import java.io.FileReader
import java.util.*

class Day5 {
    companion object {
        fun fin(sample: Boolean) = Scanner(FileReader(if (sample) "sample.txt" else "input.txt"))
    }

    class Part1(sample: Boolean) {
        val fin = fin(sample)
        fun solve() {
            val ranges = mutableListOf<LongRange>()
            while (fin.hasNextLine()) {
                val s = fin.nextLine()
                if (s.isEmpty()) break
                val range = s.split('-').map { it.toLong() }
                ranges.add(range[0]..range[1])
            }
            var ans = 0
            while (fin.hasNextLine()) {
                val x = fin.nextLine().toLong()
                if (ranges.any { x in it }) ans++
            }
            println(ans)
        }
    }

    class Part2(sample: Boolean) {
        val fin = fin(sample)
        fun solve() {
            val ranges = mutableListOf<LongRange>()
            while (fin.hasNextLine()) {
                val s = fin.nextLine()
                if (s.isEmpty()) break
                val range = s.split('-').map { it.toLong() }
                ranges.add(range[0]..range[1])
            }
            class Event(val x: Long, val type: Int) : Comparable<Event> {
                override fun compareTo(other: Event): Int {
                    var c = x.compareTo(other.x)
                    if (c == 0) c = type.compareTo(other.type)
                    return c
                }
            }

            val events = mutableListOf<Event>()
            for (r in ranges) {
                events.add(Event(r.first, 0))
                events.add(Event(r.last + 1, 1))
            }
            events.sort()
            var active = 0
            var last = 0L
            var ans = 0L
            for (e in events) {
                if (active > 0) {
                    ans += e.x - last
                }
                last = e.x
                if (e.type == 0) active++
                else active--
            }
            println(ans)
        }
    }

}

fun main() {
    Day5.Part1(sample = true).solve()
    Day5.Part1(sample = false).solve()
    Day5.Part2(sample = true).solve()
    Day5.Part2(sample = false).solve()
}
