package aoc.day15

import org.scalatest.funsuite.AnyFunSuite

import scala.io.Source

class Day15Tests extends AnyFunSuite:

  test("Day15 - Part 1") {
    assert(
      part1(Source.fromFile("src/test/resources/input_15.txt").mkString) == 517315)
  }

  test("Day15 - Part 2") {
    assert(
      part2(Source.fromFile("src/test/resources/input_15.txt").mkString) == 247763)
  }

end Day15Tests

