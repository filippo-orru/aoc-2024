import kotlin.math.absoluteValue

fun main() {
  class Robot(
    var x: Int,
    var y: Int,
    val dx: Int,
    val dy: Int,
  ) {
    fun move(fieldX: Int, fieldY: Int, factor: Int = 1) {
      x += dx
      y += dy
      x = if (x >= 0) x % fieldX else (fieldX + (x % fieldX)) % fieldX
      y = if (y >= 0) y % fieldY else (fieldY + (y % fieldY)) % fieldY
    }

    fun l1Distance(other: Robot): Int {
      return (other.x - x).absoluteValue + (other.y - y).absoluteValue
    }
  }


  fun readRobots(input: List<String>) = input.map { line ->
    val (pos, velocity) = line.split(" ")
    val (x, y) = pos.removePrefix("p=").split(",").map { it.toInt() }
    val (dx, dy) = velocity.removePrefix("v=").split(",").map { it.toInt() }
    Robot(x, y, dx, dy)
  }

  fun printField(robots: List<Robot>, fieldX: Int, fieldY: Int) {
    for (y in 0..<fieldY) {
      for (x in 0..<fieldX) {
        print(robots.count { it.x == x && it.y == y }.let { if (it == 0) "." else "$it" })
      }
      println()
    }
  }

  fun part1(input: List<String>, fieldX: Int, fieldY: Int): Int {
    val robots: List<Robot> = readRobots(input)

    val limit = 100
    robots.forEach { robot ->
      robot.move(fieldX, fieldY, factor = limit)
    }

    val quadrants = listOf(
      robots.filter { it.x < (fieldX / 2) && it.y < (fieldY / 2) },
      robots.filter { it.x > (fieldX / 2) && it.y < (fieldY / 2) },
      robots.filter { it.x < (fieldX / 2) && it.y > (fieldY / 2) },
      robots.filter { it.x > (fieldX / 2) && it.y > (fieldY / 2) }
    )

    return quadrants.fold(1) { acc, r -> acc * r.count() }
  }

  fun part2(input: List<String>, fieldX: Int, fieldY: Int): Int {
    val robots: List<Robot> = readRobots(input)

    var time = 0
    while (true) {
      time++
      robots.forEach { robot ->
        robot.move(fieldX, fieldY)
      }
      val neighborCount = robots.sumOf { robot1 ->
        robots.count { robot2 -> robot1.l1Distance(robot2) <= 2 }
      }

      if (neighborCount >= 2000) {
        printField(robots, fieldX, fieldY)
        return time
      }
      if (time % 1000 == 0) {
        println("$time ...")
      }
    }
  }

  // Test if implementation meets criteria from the description, like:
  //check(part1(listOf("test_input")) == 1)

  val testInput = readInput("Day14_test")
  check(part1(testInput, 11, 7), 12)
//  check(part2(testInput, 11, 7), 31)

  val input = readInput("Day14")
  part1(input, 101, 103).println()
  println()
  println()
  part2(input, 101, 103).println()
}
