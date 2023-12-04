package aoc.day04

import scala.collection.mutable.HashMap

def parseInts(input: String): Set[Int] =
  input.trim.split(" +").map(_.toInt).toSet

def parse(input: String): Array[(Int, Set[Int], Set[Int])] =
  input
    .split("\n")
    .collect { case s"""Card $x: $y | $z""" =>
      (x.trim.toInt, parseInts(y), parseInts(z))
    }

def part1(input: String): Int =
  parse(input)
    .map((i, win, numbers) => Math.floor(Math.pow(2, (win & numbers).size - 1)))
    .sum
    .toInt

def part2(input: String): Int = {
  val cards = parse(input)
  val res = HashMap[Int, Int](cards.map((i, _, _) => (i, 1)): _*)

  cards.foreach((i, win, numbers) => {
    println(i)
    Range(i + 1, i + (win & numbers).size + 1, 1)
      .foreach(j => res.put(j, res(j) + res(i)))
  })
  res.foreach(println(_))
  res.values.sum
}
