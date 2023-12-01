package aoc.day01

import scala.collection.mutable.HashMap

def parse(input: String): Array[String] =
  input.split("\n")

def part1(input: String): Int =
  parse(input)
    .map(s =>
      s.find(_.isDigit).get.asDigit * 10 + s.findLast(_.isDigit).get.asDigit
    )
    .sum

def part2(input: String): Int = {
  val digitPattern =
    """(\d|zero|one|two|three|four|five|six|seven|eight|nine)"""
  val firstDigitPattern = (".*?" + digitPattern + ".*").r
  val lastDigitPattern = (".*" + digitPattern + ".*?").r
  val digitAsWord = HashMap(
    "zero" -> 0,
    "one" -> 1,
    "two" -> 2,
    "three" -> 3,
    "four" -> 4,
    "five" -> 5,
    "six" -> 6,
    "seven" -> 7,
    "eight" -> 8,
    "nine" -> 9
  )

  parse(input)
    .map(s => {
      val first = s match {
        case firstDigitPattern(f) =>
          if (f.charAt(0).isDigit) f.toInt else digitAsWord.get(f).get
        case _ => 0
      }
      val last = s match {
        case lastDigitPattern(f) =>
          if (f.charAt(0).isDigit) f.toInt else digitAsWord.get(f).get
        case _ => 0
      }
      first * 10 + last
    })
    .sum
}
