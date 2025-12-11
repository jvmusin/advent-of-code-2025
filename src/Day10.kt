@file:OptIn(ExperimentalAtomicApi::class)

import java.io.FileReader
import java.time.Duration
import java.time.LocalDateTime
import java.util.*
import java.util.concurrent.Executors
import kotlin.concurrent.atomics.AtomicInt
import kotlin.concurrent.atomics.ExperimentalAtomicApi
import kotlin.concurrent.atomics.decrementAndFetch

class Day10 {
    companion object {
        fun fin(sample: Boolean) = Scanner(FileReader(if (sample) "sample.txt" else "input.txt"))
    }

    class Part1(sample: Boolean) {
        val fin = fin(sample)

        fun solveTest(s: String): Int {
            val parts = s.split(' ')
            val needState = parts[0].drop(1).dropLast(1).map { it == '#' }.toBooleanArray()
            val ways = parts.drop(1).dropLast(1).map { it.drop(1).dropLast(1).split(',').map { it -> it.toInt() } }
            var minWays = Int.MAX_VALUE
            fun dfs(at: Int, actions: Int, state: BooleanArray) {
                if (actions >= minWays) return
                if (at == ways.size) {
                    if (needState.contentEquals(state)) {
                        minWays = actions
                    }
                    return
                }
                dfs(at + 1, actions, state)
                for (i in ways[at]) {
                    state[i] = !state[i]
                }
                dfs(at + 1, actions + 1, state)
                for (i in ways[at]) {
                    state[i] = !state[i]
                }
            }
            dfs(0, 0, BooleanArray(needState.size))
            return minWays
        }

        fun solve() {
            var ans = 0
            while (fin.hasNextLine()) ans += solveTest(fin.nextLine())
            println(ans)
        }
    }

    // This solution works for about 15 minutes on MBP M4 Pro.
    // It would be nice to calculate the same testcase using different ways to choose next req,
    // it might speed up the solution drastically.
    /*
PT0.007913S (5) Got 64, Ans 255, Tasks rem: 151
PT0.012668S (13) Got 46, Ans 255, Tasks rem: 151
PT0.017233S (14) Got 81, Ans 336, Tasks rem: 150
PT0.00846S (9) Got 59, Ans 336, Tasks rem: 150
PT0.018334S (17) Got 32, Ans 368, Tasks rem: 149
PT0.021005S (11) Got 35, Ans 403, Tasks rem: 148
PT0.014742S (2) Got 11, Ans 33, Tasks rem: 0
PT0.01035S (2) Got 86, Ans 403, Tasks rem: 148
PT0.011656S (0) Got 10, Ans 33, Tasks rem: 0
PT0.014012S (1) Got 12, Ans 33, Tasks rem: 0
PT0.026557S (18) Got 50, Ans 453, Tasks rem: 147
PT0.027067S (20) Got 67, Ans 520, Tasks rem: 146
PT0.027232S (19) Got 42, Ans 562, Tasks rem: 145
PT0.028499S (22) Got 199, Ans 761, Tasks rem: 144
PT0.028611S (24) Got 27, Ans 788, Tasks rem: 143
PT0.028679S (25) Got 44, Ans 832, Tasks rem: 142
PT0.028988S (26) Got 58, Ans 890, Tasks rem: 141
PT0.031315S (0) Got 75, Ans 965, Tasks rem: 140
PT0.034815S (7) Got 115, Ans 1080, Tasks rem: 139
PT0.035304S (16) Got 87, Ans 1167, Tasks rem: 138
PT0.036109S (30) Got 132, Ans 1299, Tasks rem: 137
PT0.040524S (10) Got 69, Ans 1368, Tasks rem: 136
PT0.040661S (32) Got 73, Ans 1441, Tasks rem: 135
PT0.042155S (33) Got 90, Ans 1531, Tasks rem: 134
PT0.042771S (15) Got 184, Ans 1715, Tasks rem: 133
PT0.042843S (35) Got 56, Ans 1771, Tasks rem: 132
PT0.048908S (21) Got 58, Ans 1829, Tasks rem: 131
PT0.061118S (4) Got 72, Ans 1901, Tasks rem: 130
PT0.061215S (38) Got 16, Ans 1917, Tasks rem: 129
PT0.06547S (27) Got 105, Ans 2022, Tasks rem: 128
PT0.072808S (40) Got 71, Ans 2093, Tasks rem: 127
PT0.072866S (41) Got 19, Ans 2112, Tasks rem: 126
PT0.072915S (42) Got 5, Ans 2117, Tasks rem: 125
PT0.072965S (43) Got 55, Ans 2172, Tasks rem: 124
PT0.07362S (44) Got 70, Ans 2242, Tasks rem: 123
PT0.073778S (45) Got 44, Ans 2286, Tasks rem: 122
PT0.073891S (46) Got 93, Ans 2379, Tasks rem: 121
PT0.073958S (47) Got 35, Ans 2414, Tasks rem: 120
PT0.083125S (37) Got 78, Ans 2492, Tasks rem: 119
PT0.083394S (49) Got 196, Ans 2688, Tasks rem: 118
PT0.083434S (50) Got 12, Ans 2700, Tasks rem: 117
PT0.089818S (12) Got 191, Ans 2891, Tasks rem: 116
PT0.089865S (52) Got 33, Ans 2924, Tasks rem: 115
PT0.097598S (36) Got 262, Ans 3186, Tasks rem: 114
PT0.10304S (54) Got 267, Ans 3453, Tasks rem: 113
PT0.103087S (55) Got 40, Ans 3493, Tasks rem: 112
PT0.105063S (56) Got 77, Ans 3570, Tasks rem: 111
PT0.105901S (57) Got 61, Ans 3631, Tasks rem: 110
PT0.105937S (58) Got 12, Ans 3643, Tasks rem: 109
PT0.107325S (1) Got 69, Ans 3712, Tasks rem: 108
PT0.107383S (60) Got 22, Ans 3734, Tasks rem: 107
PT0.107451S (61) Got 57, Ans 3791, Tasks rem: 106
PT0.107491S (62) Got 5, Ans 3796, Tasks rem: 105
PT0.107534S (63) Got 45, Ans 3841, Tasks rem: 104
PT0.10783S (48) Got 96, Ans 4027, Tasks rem: 102
PT0.107769S (31) Got 90, Ans 4027, Tasks rem: 102
PT0.107917S (65) Got 38, Ans 4065, Tasks rem: 101
PT0.135964S (51) Got 143, Ans 4208, Tasks rem: 100
PT0.138114S (68) Got 95, Ans 4303, Tasks rem: 99
PT0.146381S (28) Got 79, Ans 4382, Tasks rem: 98
PT0.20221S (69) Got 241, Ans 4623, Tasks rem: 97
PT0.202315S (71) Got 44, Ans 4667, Tasks rem: 96
PT0.20246S (72) Got 57, Ans 4724, Tasks rem: 95
PT0.202878S (73) Got 227, Ans 4951, Tasks rem: 94
PT0.21541S (70) Got 173, Ans 5124, Tasks rem: 93
PT0.249396S (75) Got 80, Ans 5204, Tasks rem: 92
PT0.250695S (76) Got 43, Ans 5247, Tasks rem: 91
PT0.304458S (3) Got 106, Ans 5353, Tasks rem: 90
PT0.304654S (78) Got 63, Ans 5416, Tasks rem: 89
PT0.31165S (23) Got 93, Ans 5509, Tasks rem: 88
PT0.311733S (80) Got 49, Ans 5558, Tasks rem: 87
PT0.321071S (81) Got 75, Ans 5633, Tasks rem: 86
PT0.339135S (82) Got 76, Ans 5709, Tasks rem: 85
PT0.339189S (83) Got 49, Ans 5758, Tasks rem: 84
PT0.344704S (8) Got 97, Ans 5855, Tasks rem: 83
PT0.344766S (85) Got 40, Ans 5895, Tasks rem: 82
PT0.347937S (86) Got 174, Ans 6069, Tasks rem: 81
PT0.348033S (87) Got 42, Ans 6111, Tasks rem: 80
PT0.348327S (88) Got 64, Ans 6175, Tasks rem: 79
PT0.34846S (89) Got 54, Ans 6229, Tasks rem: 78
PT0.348636S (90) Got 58, Ans 6287, Tasks rem: 77
PT0.348677S (91) Got 15, Ans 6302, Tasks rem: 76
PT0.348718S (92) Got 20, Ans 6322, Tasks rem: 75
PT0.395513S (77) Got 99, Ans 6421, Tasks rem: 74
PT0.396427S (94) Got 50, Ans 6471, Tasks rem: 73
PT0.424004S (93) Got 101, Ans 6572, Tasks rem: 72
PT0.424107S (96) Got 46, Ans 6618, Tasks rem: 71
PT0.426916S (97) Got 81, Ans 6699, Tasks rem: 70
PT0.427032S (98) Got 46, Ans 6745, Tasks rem: 69
PT0.427073S (99) Got 33, Ans 6778, Tasks rem: 68
PT0.429132S (100) Got 69, Ans 6847, Tasks rem: 67
PT0.429958S (101) Got 155, Ans 7002, Tasks rem: 66
PT0.429995S (102) Got 52, Ans 7054, Tasks rem: 65
PT0.515353S (74) Got 246, Ans 7300, Tasks rem: 64
PT0.531973S (79) Got 93, Ans 7393, Tasks rem: 63
PT0.536249S (105) Got 115, Ans 7508, Tasks rem: 62
PT0.536794S (106) Got 233, Ans 7741, Tasks rem: 61
PT0.537543S (107) Got 138, Ans 7879, Tasks rem: 60
PT0.540197S (104) Got 67, Ans 7946, Tasks rem: 59
PT0.540577S (109) Got 63, Ans 8009, Tasks rem: 58
PT0.541358S (108) Got 99, Ans 8108, Tasks rem: 57
PT0.541402S (111) Got 28, Ans 8136, Tasks rem: 56
PT0.541568S (112) Got 33, Ans 8169, Tasks rem: 55
PT0.541621S (113) Got 39, Ans 8208, Tasks rem: 54
PT0.958088S (66) Got 109, Ans 8317, Tasks rem: 53
PT0.962178S (115) Got 87, Ans 8404, Tasks rem: 52
PT0.972882S (116) Got 64, Ans 8468, Tasks rem: 51
PT0.974314S (117) Got 107, Ans 8575, Tasks rem: 50
PT0.974356S (118) Got 32, Ans 8607, Tasks rem: 49
PT1.447797S (84) Got 110, Ans 8717, Tasks rem: 48
PT1.455221S (120) Got 190, Ans 8907, Tasks rem: 47
PT1.455305S (121) Got 55, Ans 8962, Tasks rem: 46
PT1.899594S (110) Got 113, Ans 9075, Tasks rem: 45
PT2.481968S (6) Got 191, Ans 9266, Tasks rem: 44
PT2.482236S (124) Got 262, Ans 9528, Tasks rem: 43
PT2.822102S (39) Got 274, Ans 9802, Tasks rem: 42
PT2.826252S (126) Got 93, Ans 9895, Tasks rem: 41
PT2.826584S (127) Got 197, Ans 10092, Tasks rem: 40
PT2.826676S (128) Got 51, Ans 10143, Tasks rem: 39
PT2.826712S (129) Got 52, Ans 10195, Tasks rem: 38
PT2.826764S (130) Got 39, Ans 10234, Tasks rem: 37
PT2.826808S (131) Got 67, Ans 10301, Tasks rem: 36
PT2.827472S (132) Got 85, Ans 10386, Tasks rem: 35
PT2.830565S (133) Got 65, Ans 10451, Tasks rem: 34
PT2.832054S (134) Got 47, Ans 10498, Tasks rem: 33
PT2.964719S (135) Got 111, Ans 10609, Tasks rem: 32
PT2.965689S (136) Got 81, Ans 10690, Tasks rem: 31
PT2.973487S (137) Got 206, Ans 10896, Tasks rem: 30
PT3.040892S (125) Got 94, Ans 10990, Tasks rem: 29
PT3.040985S (139) Got 183, Ans 11173, Tasks rem: 28
PT3.041844S (140) Got 65, Ans 11238, Tasks rem: 27
PT3.042048S (141) Got 82, Ans 11320, Tasks rem: 26
PT4.241602S (59) Got 109, Ans 11429, Tasks rem: 25
PT4.419656S (143) Got 96, Ans 11525, Tasks rem: 24
PT4.419838S (144) Got 43, Ans 11568, Tasks rem: 23
PT4.421663S (145) Got 99, Ans 11667, Tasks rem: 22
PT4.421719S (146) Got 91, Ans 11758, Tasks rem: 21
PT4.478658S (142) Got 210, Ans 11968, Tasks rem: 20
PT4.506714S (147) Got 96, Ans 12064, Tasks rem: 19
PT4.506849S (149) Got 38, Ans 12102, Tasks rem: 18
PT4.506898S (150) Got 215, Ans 12317, Tasks rem: 17
PT4.55663S (148) Got 92, Ans 12409, Tasks rem: 16
PT4.561343S (152) Got 98, Ans 12507, Tasks rem: 15
PT4.605795S (153) Got 95, Ans 12602, Tasks rem: 14
PT4.605855S (154) Got 24, Ans 12626, Tasks rem: 13
PT5.178317S (64) Got 152, Ans 12778, Tasks rem: 12
PT6.830503S (103) Got 108, Ans 12886, Tasks rem: 11
PT7.278274S (151) Got 123, Ans 13009, Tasks rem: 10
PT8.049676S (34) Got 90, Ans 13099, Tasks rem: 9
PT15.367944S (29) Got 104, Ans 13203, Tasks rem: 8
PT22.462257S (122) Got 283, Ans 13486, Tasks rem: 7
PT30.888614S (95) Got 117, Ans 13603, Tasks rem: 6
PT55.665665S (123) Got 94, Ans 13697, Tasks rem: 5
PT1M29.006261S (138) Got 156, Ans 13853, Tasks rem: 4
PT2M0.621381S (114) Got 143, Ans 13996, Tasks rem: 3
PT3M2.642031S (53) Got 143, Ans 14139, Tasks rem: 2
PT3M21.583873S (119) Got 307, Ans 14446, Tasks rem: 1
PT15M23.439074S (67) Got 231, Ans 14677, Tasks rem: 0
     */
    class Part2(sample: Boolean) {
        val fin = fin(sample)

        class Solver(s: String) {
            val parts = s.split(' ')
            fun parseString(it: String) = it.drop(1).dropLast(1).split(',').map { it.toInt() }.toIntArray()
            var ways =
                parts.drop(1).dropLast(1).map { parseString(it) }.toTypedArray()
            val requirements = parseString(parts.last())
            val reqIndexToWays = Array(requirements.size) { mutableListOf<Int>() }.let { reqIndexToWays ->
                for (i in ways.indices) {
                    for (j in ways[i]) {
                        reqIndexToWays[j].add(i)
                    }
                }
                reqIndexToWays.map { it.toIntArray() }.toTypedArray()
            }
            var minActions = Int.MAX_VALUE

            fun dfs(actions: Int, remTotal: Int, useReq: Int, useReqWayIndex: Int) {
                if (actions >= minActions) return
                if (remTotal == 0) {
                    minActions = actions
                    return
                }

                var useReq = useReq
                var useReqWayIndex = useReqWayIndex
                if (requirements[useReq] != 0) {
                    if (useReqWayIndex == reqIndexToWays[useReq].size) {
                        return
                    }
                } else {
                    useReqWayIndex = 0
                    useReq = -1
                    for (i in requirements.indices) {
                        if (requirements[i] > 0) {
                            if (useReq == -1 || (reqIndexToWays[i].size < reqIndexToWays[useReq].size || (reqIndexToWays[i].size == reqIndexToWays[useReq].size && requirements[i] < requirements[useReq]))) {
                                useReq = i
                            }
                        }
                    }
                }
                val wayIndex = reqIndexToWays[useReq][useReqWayIndex]
                var canUse = true
                for (i in ways[wayIndex]) if (requirements[i] == 0) canUse = false
                if (canUse) {
                    for (i in ways[wayIndex]) {
                        requirements[i]--
                    }
                    dfs(
                        actions = actions + 1,
                        remTotal = remTotal - ways[wayIndex].size,
                        useReq = useReq,
                        useReqWayIndex = useReqWayIndex
                    )
                    for (i in ways[wayIndex]) requirements[i]++
                }
                dfs(
                    actions = actions,
                    remTotal = remTotal,
                    useReq = useReq,
                    useReqWayIndex = useReqWayIndex + 1
                )
            }

            fun solve(): Int {
                dfs(
                    actions = 0,
                    remTotal = requirements.sum(),
                    useReq = requirements.indexOf(requirements.filter { it != 0 }.min()),
                    useReqWayIndex = 0
                )
                return minActions
            }
        }

        fun solveTest(s: String): Int {
            return Solver(s).solve()
        }

        fun solve() {
            val ans = AtomicInt(0)
            val inp = mutableListOf<String>()
            while (fin.hasNextLine()) inp += fin.nextLine()
            val start = LocalDateTime.now()
            val pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())
            val tasksRem = AtomicInt(inp.size)
            for (i in inp.indices) {
                pool.submit {
                    val t = solveTest(inp[i])
                    ans.addAndFetch(t)
                    tasksRem.decrementAndFetch()
                    val time = Duration.between(start, LocalDateTime.now())
                    println("$time ($i) Got $t, Ans $ans, Tasks rem: $tasksRem")
                }
            }
            pool.shutdown()
            println(ans)
        }
    }
}

fun main() {
    Day10.Part1(sample = true).solve()
    Day10.Part1(sample = false).solve()
    Day10.Part2(sample = true).solve()
    Day10.Part2(sample = false).solve()
}
