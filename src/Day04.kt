fun main() {
  fun part1(input: List<String>): Int {
    val grid: List<List<String>> = input.map { it.split("").drop(1).dropLast(1) }
    var count = 0
    val d = listOf(-1, 0, 1)
    val word = listOf("M", "A", "S")

    grid.forEachIndexed { y, row ->
      row.forEachIndexed { x, cell ->
        if (cell == "X") {
          for (dx in d) {
            for (dy in d) {
              var wordOk = true
              for (l in 1..3) {
                val offsetY = y + dy * l
                val offsetX = x + dx * l
                if (offsetY !in grid.indices || offsetX !in row.indices) {
                  wordOk = false
                  break
                }
                val offsetCell = grid[offsetY][offsetX]
                if (offsetCell != word[l - 1]) {
                  wordOk = false
                  break
                }
              }
              if (wordOk) {
                count += 1
              }
            }
          }
        }
      }
    }
    return count
  }

  fun part2(input: List<String>): Int {
    val grid: List<List<String>> = input.map { it.split("").drop(1).dropLast(1) }
    var count = 0
    val d = listOf(-1, 1)
    grid.forEachIndexed { y, row ->
      row.forEachIndexed next@{ x, cell ->
        if (cell == "A") {
          var m = 0
          var s = 0
          for (dx in d) {
            for (dy in d) {
              val offsetY = y + dy
              val offsetX = x + dx
              if (offsetY !in grid.indices || offsetX !in row.indices) {
                return@next
              }
              val offsetCell = grid[offsetY][offsetX]
              when (offsetCell) {
                "M" -> m += 1
                "S" -> s += 1
                else -> return@next
              }
            }
          }
          if (m == 2 && s == 2) {
            count += 1
          }
        }
      }
    }

    return count
  }

  // Test if implementation meets criteria from the description, like:
  //check(part1(listOf("test_input")) == 1)

  check(part1(readInput("Day04_test1")), 18)
  check(part2(readInput("Day04_test2")), 9)

  val input = readInput("Day04")
  part1(input).println()
  part2(input).println()
}
