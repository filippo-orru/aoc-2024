import kotlin.math.absoluteValue
import kotlin.math.sign

fun main() {
  fun part1(input: List<String>): Int {
    val safeCount = input.count { line ->
      var previous: Int? = null
      var increasing: Int? = null

      for (levelString in line.split(" ")) {
        val level = levelString.toInt()
        if (previous != null) {
          if (increasing == null) {
            increasing = level - previous
          }

          val diff: Int = level - previous

          if (diff.absoluteValue !in 1..3 || (diff.sign != increasing.sign)) {
            return@count false
          }
        }
        previous = level
      }
      return@count true
    }
    return safeCount
  }

  fun part2(input: List<String>): Int {
    fun isLineSafe(input: List<String>): Boolean {
      var previous: Int? = null
      var increasing: Int? = null

      for (levelString in input) {
        val level = levelString.toInt()
        if (previous != null) {
          if (increasing == null) {
            increasing = level - previous!!
          }

          val diff: Int = level - previous!!

          if (diff.absoluteValue !in 1..3 || (diff.sign != increasing!!.sign)) {
            return false
          }
        }
        previous = level
      }
      return true
    }

    val safeCount = input.count { line ->
      val levels = line.split(" ")
      for (i in -1..levels.size) {
        val sublist = if (i == -1) {
          levels
        } else if (i == 0) {
          levels.drop(1)
        } else if (i == levels.size) {
          levels.dropLast(1)
        } else {
          levels.subList(0, i) + levels.subList(i + 1, levels.size)
        }
        if (isLineSafe(sublist)) {
          return@count true
        }
      }
      return@count false
    }
    return safeCount
  }

  // Test if implementation meets criteria from the description, like:
  //check(part1(listOf("test_input")) == 1)

  val testInput = readInput("Day02_test")
  check(part1(testInput), 2)
  check(part2(testInput), 4)

  val input = readInput("Day02")
  part1(input).println()
  part2(input).println()
}
