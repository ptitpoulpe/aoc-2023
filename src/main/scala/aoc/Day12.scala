package aoc.day12

import scala.collection.mutable

def parse(input: String): List[(List[Char], List[Int])] =
  input
    .split("\n")
    .collect { case s"$symbols $numbers" =>
      (symbols.toList, numbers.split(",").map(_.toInt).toList)
    }
    .toList

def memoize[I, O](f: I => O): I => O = new mutable.HashMap[I, O]() {
  override def apply(key: I) = getOrElseUpdate(key, f(key))
}

lazy val count: ((List[Char], List[Int])) => Long = memoize {
  case (symbols, List()) => if (symbols.contains('#')) 0L else 1L
  case (symbols, head :: tail) => {
    val remaining = tail.sum + tail.length
    Range
      .inclusive(0, symbols.length - head - remaining)
      .filter(i => {
        symbols.slice(0, i).forall(_ != '#') &
          symbols.slice(i, i + head).forall(_ != '.') &
          symbols.orElse { _ => '.' }(i + head) != '#'
      })
      .map(i => count(symbols.drop(i + head + 1), tail))
      .sum
  }
}

def part1(input: String): Long =
  parse(input)
    .map((symbols, numbers) => count(symbols, numbers))
    .sum

def part2(input: String): Long =
  parse(input)
    .map((symbols, numbers) =>
      count(
        symbols ++ "?" ++ symbols ++ "?" ++ symbols ++ "?" ++ symbols ++ "?" ++ symbols,
        numbers ++ numbers ++ numbers ++ numbers ++ numbers
      )
    )
    .sum
