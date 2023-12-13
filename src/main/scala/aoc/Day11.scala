package aoc.day11

def parse(input: String): Array[Array[Char]] =
  input
    .split("\n")
    .map(_.toArray)

def part1(input: String): Int = {
  val map = parse(input)
  val emptyRows = map.zipWithIndex
    .filter((row, i) => !row.contains('#'))
    .map(_._2)
    .toList
  val emptyCols = map.transpose.zipWithIndex
    .filter((col, i) => !col.contains('#'))
    .map(_._2)
    .toList
  val galaxies = map.zipWithIndex
    .map((row, rowId) =>
      row.zipWithIndex
        .filter((cell, _) => cell == '#')
        .map((_, colId) => (rowId, colId))
    )
    .flatten
    .toList
  galaxies
    .combinations(2)
    .collect {
      case List((r1, c1), (r2, c2)) => {
        val rs = List(r1, r2).sorted
        val rmin = rs.head
        val rmax = rs.last
        val cs = List(c1, c2).sorted
        val cmin = cs.head
        val cmax = cs.last
        (
          rmax - rmin +
            cmax - cmin +
            Range(rmin + 1, rmax).filter(emptyRows.contains(_)).length +
            Range(cmin + 1, cmax).filter(emptyCols.contains(_)).length
        )
      }
    }
    .sum
}

def part2(input: String): Long = {
  val map = parse(input)
  val emptyRows = map.zipWithIndex
    .filter((row, i) => !row.contains('#'))
    .map(_._2.toLong)
    .toList
  val emptyCols = map.transpose.zipWithIndex
    .filter((col, i) => !col.contains('#'))
    .map(_._2.toLong)
    .toList
  val galaxies = map.zipWithIndex
    .map((row, rowId) =>
      row.zipWithIndex
        .filter((cell, _) => cell == '#')
        .map((_, colId) => (rowId.toLong, colId.toLong))
    )
    .flatten
    .toList
  galaxies
    .combinations(2)
    .collect {
      case List((r1, c1), (r2, c2)) => {
        val rs = List(r1, r2).sorted
        val rmin = rs.head
        val rmax = rs.last
        val cs = List(c1, c2).sorted
        val cmin = cs.head
        val cmax = cs.last
        (
          rmax - rmin +
            cmax - cmin +
            (rmin + 1 to rmax - 1)
              .filter(emptyRows.contains(_))
              .length * (1_000_000 - 1) +
            (cmin + 1 to cmax - 1)
              .filter(emptyCols.contains(_))
              .length * (1_000_000 - 1)
        )
      }
    }
    .sum
}
