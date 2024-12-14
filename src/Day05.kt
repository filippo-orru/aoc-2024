fun main() {
  fun part1(input: List<String>): Int {
    val ruleLines = input.takeWhile { it != "" }
    val printLines = input.drop(ruleLines.size + 1)

    val pageToMustAppearPreviously = mutableMapOf<String, ArrayList<String>>()
    for (line in ruleLines) {
      val (left, right) = line.split("|")
      val list = pageToMustAppearPreviously.getOrPut(right) { ArrayList() }
      list.add(left)
    }

    var sum = 0
    for (line in printLines) {
      val pages = line.split(",")
      var allOk = true
      pages.forEachIndexed { index, page ->
        val previousPages = pages.subList(0, index)
        val mustPreviouslyAppear = pageToMustAppearPreviously[page] ?: listOf()
        if (mustPreviouslyAppear.filter { pages.contains(it) }.any { !previousPages.contains(it) }) {
          allOk = false
          return@forEachIndexed
        }
      }
      if (allOk) {
        sum += pages[pages.size / 2].toInt()
      }
    }

    return sum
  }

  fun part2(input: List<String>): Int {
    val ruleLines = input.takeWhile { it != "" }
    val printLines = input.drop(ruleLines.size + 1)

    val pageToMustAppearPreviously = mutableMapOf<String, ArrayList<String>>()
    for (line in ruleLines) {
      val (left, right) = line.split("|")
      val list = pageToMustAppearPreviously.getOrPut(right) { ArrayList() }
      list.add(left)
    }

    var sum = 0
    for (line in printLines) {
      var pages = line.split(",")
      outer@ while (true) {
        val pageWaitingForPage = mutableMapOf<String, ArrayList<String>>()
        for ((index, page) in pages.withIndex()) {
          val previousPages = pages.subList(0, index)
          val mustPreviouslyAppear = pageToMustAppearPreviously[page]?.filter { pages.contains(it) } ?: listOf()
          for (previousPage in previousPages) {
            // TODO
          }
        }
        break@outer
      }
      sum += pages[pages.size / 2].toInt()
    }

    return sum
  }

  // Test if implementation meets criteria from the description, like:
  //check(part1(listOf("test_input")) == 1)

  val testInput = readInput("Day05_test")
  check(part1(testInput), 143)
//  check(part2(testInput), 31)

  val input = readInput("Day05")
  part1(input).println()
  part2(input).println()
}
