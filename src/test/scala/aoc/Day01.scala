package aoc.day01

import org.scalatest.funsuite.AnyFunSuite

import scala.io.Source

class Day01Tests extends AnyFunSuite:

  test("Day01 - Part 1") {
    assert(
      part1(Source.fromFile("src/test/resources/input_01.txt").mkString) == 55208)
  }

  test("Day01 - Part 2") {
    assert(
      part2(Source.fromFile("src/test/resources/input_01.txt").mkString) == 54578)
  }

end Day01Tests

