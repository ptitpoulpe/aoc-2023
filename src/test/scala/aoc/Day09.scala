package aoc.day09

import org.scalatest.funsuite.AnyFunSuite

import scala.io.Source

class Day09Tests extends AnyFunSuite:

  test("Day09 - Part 1") {
    assert(
      part1(Source.fromFile("src/test/resources/input_09.txt").mkString) == 2043183816)
  }

  test("Day09 - Part 2") {
    assert(
      part2(Source.fromFile("src/test/resources/input_09.txt").mkString) == 1118)
  }

end Day09Tests

