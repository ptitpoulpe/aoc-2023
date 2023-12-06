package aoc.day06

import org.scalatest.funsuite.AnyFunSuite

import scala.io.Source

class Day06Tests extends AnyFunSuite:

  test("Day06 - Part 1") {
    assert(
      part1(Source.fromFile("src/test/resources/input_06.txt").mkString) == 2756160)
  }

  test("Day06 - Part 2") {
    assert(
      part2(Source.fromFile("src/test/resources/input_06.txt").mkString) == 34788142)
  }

end Day06Tests

