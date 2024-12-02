fun main() {
  fun part1(input: List<String>): Int {
    return 0 // TODO
  }

  fun part2(input: List<String>): Int {
    return 0 // TODO
  }

  // Test if implementation meets criteria from the description, like:
  //check(part1(listOf("test_input")) == 1)

  val testInput = readInput("DayXXX_test")
  check(part1(testInput), 2)
  check(part2(testInput), 31)

  val input = readInput("DayXXX")
  part1(input).println()
  part2(input).println()
}
