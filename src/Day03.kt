fun main() {
  fun part1(input: List<String>): Int {
    val regex = Regex("""mul\((\d+),(\d+)\)""")
    return input.sumOf { line ->
      regex.findAll(line).sumOf { matchResult ->
        matchResult.groups[1]!!.value.toInt() * matchResult.groups[2]!!.value.toInt()
      }
    }
  }

  fun part2(input: List<String>): Int {
    val mulRegex = Regex("""mul\((\d+),(\d+)\)""")
    val dontStr = "don't()"
    val doStr = "do()"
    val allLines = input.joinToString("")
    var sum = 0
    var line = allLines
    while (line.isNotEmpty()) {
      val substring = line.substringBefore(dontStr)
      sum += mulRegex.findAll(substring).sumOf { matchResult ->
        matchResult.groups[1]!!.value.toInt() * matchResult.groups[2]!!.value.toInt()
      }
      line = line.substringAfter(dontStr, "").substringAfter(doStr, "")
    }
    return sum
  }

  // Test if implementation meets criteria from the description, like:
  //check(part1(listOf("test_input")) == 1)

  //  val testInput = readInput("Day03_test")
  //  check(part1(testInput), 2)
  //  check(part2(testInput), 31)

  val input = readInput("Day03")
  part1(input).println()
  part2(input).println()
}
