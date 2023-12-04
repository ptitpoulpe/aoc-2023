package aoc.day04

import org.scalatest.funsuite.AnyFunSuite

import scala.io.Source

class Day04Tests extends AnyFunSuite:

  test("Day04 - Part 1") {
    assert(
      part1(Source.fromFile("src/test/resources/input_04.txt").mkString) == 21105)
  }

  test("Day04 - Part 2") {
    assert(
      part2(Source.fromFile("src/test/resources/input_04.txt").mkString) == 5329815)
  }

end Day04Tests

