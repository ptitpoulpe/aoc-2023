package aoc.day14

import org.scalatest.funsuite.AnyFunSuite

import scala.io.Source

class Day14Tests extends AnyFunSuite:

  test("Day14 - Part 1") {
    assert(
      part1(Source.fromFile("src/test/resources/input_14.txt").mkString) == 105249)
  }

  test("Day14 - Part 2") {
    assert(
      part2(Source.fromFile("src/test/resources/input_14.txt").mkString) == 88680)
  }

end Day14Tests

