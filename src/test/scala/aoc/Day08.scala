package aoc.day08

import org.scalatest.funsuite.AnyFunSuite

import scala.io.Source

class Day08Tests extends AnyFunSuite:

  test("Day08 - Part 1") {
    assert(
      part1(Source.fromFile("src/test/resources/input_08.txt").mkString) == 18157)
  }

  test("Day08 - Part 2") {
    assert(
      part2(Source.fromFile("src/test/resources/input_08.txt").mkString) == 14299763833181L)
  }

end Day08Tests

