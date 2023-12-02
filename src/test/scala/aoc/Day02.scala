package aoc.day02

import org.scalatest.funsuite.AnyFunSuite

import scala.io.Source

class Day02Tests extends AnyFunSuite:

  test("Day02 - Part 1") {
    assert(
      part1(Source.fromFile("src/test/resources/input_02.txt").mkString) == 2512)
  }

  test("Day02 - Part 2") {
    assert(
      part2(Source.fromFile("src/test/resources/input_02.txt").mkString) == 10)
  }

end Day02Tests

