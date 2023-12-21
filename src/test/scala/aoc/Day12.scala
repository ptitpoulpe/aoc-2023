package aoc.day12

import org.scalatest.funsuite.AnyFunSuite

import scala.io.Source

class Day12Tests extends AnyFunSuite:

  test("Day12 - Part 1") {
    assert(
      part1(Source.fromFile("src/test/resources/input_12.txt").mkString) == 7718)
  }

  test("Day12 - Part 2") {
    assert(
      part2(Source.fromFile("src/test/resources/input_12.txt").mkString) == 128741994134728L)
  }

end Day12Tests

