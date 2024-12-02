import kotlin.math.absoluteValue

fun main() {
  fun part1(input: List<String>): Int {
    val left = mutableListOf<String>()
    val right = mutableListOf<String>()

    input.forEach { line ->
      val (l, r) = line.split("   ")
      left.add(l)
      right.add(r)
    }

    left.sort()
    right.sort()

    var sum = 0

    left.forEachIndexed { i, l ->
      val r = right[i]
      val diff = (l.toInt() - r.toInt()).absoluteValue
      sum += diff
    }

    return sum
  }

  fun part2(input: List<String>): Int {
    val left = mutableListOf<String>()
    val right = mutableListOf<String>()

    input.forEach { line ->
      val (l, r) = line.split("   ")
      left.add(l)
      right.add(r)
    }

    var sum = 0
    left.forEach { l ->
      val count = right.count { r -> l == r }
      sum += l.toInt() * count
    }

    return sum
  }

  // Test if implementation meets criteria from the description, like:
//  check(part1(listOf("test_input")) == 1)

  // Or read a large test input from the `src/Day01_test.txt` file:
  val testInput = readInput("Day01_test")
  check(part1(testInput) == 11)
  check(part2(testInput) == 31)

  // Read the input from the `src/Day01.txt` file.
  val input = readInput("Day01")
  part1(input).println()
  part2(input).println()
}
