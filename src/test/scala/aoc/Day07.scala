package aoc.day07

import org.scalatest.funsuite.AnyFunSuite

import scala.io.Source

class Day07Tests extends AnyFunSuite:

  test("Day07 - Part 1") {
    assert(
      part1(Source.fromFile("src/test/resources/input_07.txt").mkString) == 246409899)
  }

  test("Day07 - Part 2") {
    assert(
      part2(Source.fromFile("src/test/resources/input_07.txt").mkString) == 244848487)
  }

end Day07Tests

