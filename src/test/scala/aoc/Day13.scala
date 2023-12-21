package aoc.day13

import org.scalatest.funsuite.AnyFunSuite

import scala.io.Source

class Day13Tests extends AnyFunSuite:

  test("Day13 - Part 1") {
    assert(
      part1(Source.fromFile("src/test/resources/input_13.txt").mkString) == 34911)
  }

  test("Day13 - Part 2") {
    assert(
      part2(Source.fromFile("src/test/resources/input_13.txt").mkString) == 33183)
  }

end Day13Tests

