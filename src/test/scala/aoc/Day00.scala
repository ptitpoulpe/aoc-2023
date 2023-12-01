package aoc.day00

import org.scalatest.funsuite.AnyFunSuite

import scala.io.Source

class Day00Tests extends AnyFunSuite:

  test("Day00 - Part 1") {
    assert(
      part1(Source.fromFile("src/test/resources/input_00.txt").mkString) == 10)
  }

  test("Day00 - Part 2") {
    assert(
      part2(Source.fromFile("src/test/resources/input_00.txt").mkString) == 10)
  }

end Day00Tests

