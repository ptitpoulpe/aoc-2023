package aoc.day03

import org.scalatest.funsuite.AnyFunSuite

import scala.io.Source

class Day03Tests extends AnyFunSuite:

  test("Day03 - Part 1") {
    assert(
      part1(Source.fromFile("src/test/resources/input_03.txt").mkString) == 560670)
  }

  test("Day03 - Part 2") {
    assert(
      part2(Source.fromFile("src/test/resources/input_03.txt").mkString) == 91622824)
  }

end Day03Tests

