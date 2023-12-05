package aoc.day05

import org.scalatest.funsuite.AnyFunSuite

import scala.io.Source

class Day05Tests extends AnyFunSuite:

  test("Day05 - Part 1") {
    assert(
      part1(Source.fromFile("src/test/resources/input_05.txt").mkString) == 324724204)
  }

  test("Day05 - Part 2") {
    assert(
      part2(Source.fromFile("src/test/resources/input_05.txt").mkString) == 104070862)
  }

end Day05Tests

