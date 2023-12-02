package aoc.day02

def parseSets(input: String): Array[Map[String, Int]] = {
  val regex = """\s*(\d+) (\w+)\s*""".r
  input
    .split(";")
    .map(
      _.split(",")
        .collect { case regex(num, color) =>
          (color, num.toInt)
        }
        .toMap
    )
}

def parse(input: String): List[(Int, Array[Map[String, Int]])] = {
  val gameRegex = """Game (\d+): (.*)""".r
  input
    .split("\n")
    .collect { case gameRegex(gameId, sets) => (gameId.toInt, parseSets(sets)) }
    .toList
}

def part1(input: String): Int =
  parse(input)
    .filter((gameId, sets) =>
      sets.forall(set =>
        set.getOrElse("red", 0) <= 12
          & set.getOrElse("green", 0) <= 13
          & set.getOrElse("blue", 0) <= 14
      )
    )
    .map((gameId, sets) => gameId)
    .sum

def part2(input: String): Int =
  parse(input)
    .map((gameId, sets) =>
      sets.map(_.getOrElse("red", 0)).max
        * sets.map(_.getOrElse("green", 0)).max
        * sets.map(_.getOrElse("blue", 0)).max
    )
    .sum
