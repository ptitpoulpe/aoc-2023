package aoc.day13

def toInt(bools: Array[Boolean]): Int =
  bools.foldLeft(0)((elm, acc) => (elm << 1) + (if (acc) 1 else 0))

class Grid(grid: Array[Array[Boolean]]) {
  def nbRows(): Int = grid.length
  def transpose(): Grid = Grid(grid.transpose)

  def mirror(): Int = {
    val rowGroups = grid.zipWithIndex
      .groupMap((row, _) => toInt(row))((_, i) => i)
      .values
      .filter(_.length > 1)
      .map(_.combinations(2))
      .flatten
      .collect { case Array(x, y) =>
        (x, y)
      }
      .toList
    var res = 0
    rowGroups
      .collect {
        case (x: Int, y: Int) if y == x + 1 =>
          if (
            LazyList
              .from(1)
              .takeWhile(s => 0 <= x - s & y + s < nbRows())
              .forall(s => rowGroups.contains((x - s, y + s)))
          )
            res = x + 1
      }
    res
  }

  def firstMirror(): Int =
    Range(0, nbRows())
      .find(i => {
        LazyList
          .from(0)
          .takeWhile(s => 0 <= i - s & i + 1 + s < nbRows())
          .map(s =>
            grid(i - s)
              .zip(grid(i + 1 + s))
              .filter(pair => pair(0) != pair(1))
              .length
          )
          .sum == 1
      })
      .map(_ + 1)
      .getOrElse(0)
}

object Grid {
  def parse(input: String): Grid =
    Grid(
      input
        .split("\n")
        .map(row => row.map(char => char == '#').toArray)
    )
}

def parse(input: String): Array[Grid] =
  input
    .split("\n\n")
    .map(Grid.parse(_))

def part1(input: String): Int =
  parse(input)
    .map(grid => grid.mirror() * 100 + grid.transpose().mirror())
    .sum

def part2(input: String): Int =
  parse(input)
    .map(grid => grid.firstMirror() * 100 + grid.transpose().firstMirror())
    .sum
