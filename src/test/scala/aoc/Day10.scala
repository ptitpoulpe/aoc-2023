package aoc.day10

import org.scalatest.funsuite.AnyFunSuite

import scala.io.Source

class Day10Tests extends AnyFunSuite:

  test("Day10 - Part 1") {
    assert(
      part1(Source.fromFile("src/test/resources/input_10.txt").mkString) == 7107)
  }

  test("Day10 - Part 2") {
    assert(
      part2(Source.fromFile("src/test/resources/input_10.txt").mkString) == 281)
  }

end Day10Tests

