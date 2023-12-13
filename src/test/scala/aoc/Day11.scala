package aoc.day11

import org.scalatest.funsuite.AnyFunSuite

import scala.io.Source

class Day11Tests extends AnyFunSuite:

  test("Day11 - Part 1") {
    assert(
      part1(Source.fromFile("src/test/resources/input_11.txt").mkString) == 10228230)
  }

  test("Day11 - Part 2") {
    assert(
      part2(Source.fromFile("src/test/resources/input_11.txt").mkString) == 447073334102L)
  }

end Day11Tests

