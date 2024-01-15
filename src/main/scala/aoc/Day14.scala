package aoc.day14

def parse(input: String): Array[Array[Char]] =
  input
    .split("\n")
    .map(row => row.toArray)

def part1(input: String): Int =
  parse(input).transpose
    .map(column =>
      column.mkString
        .split("#")
        .foldLeft((0, 0))((acc, elm) =>
          (
            acc._1 + elm.length + 1,
            acc._2 + Range(
              column.length - elm.count(_ == 'O') - acc._1 + 1,
              column.length - acc._1 + 1
            ).sum
          )
        )
        ._2
    )
    .sum

def part2(input: String): Int = {
  var graph = parse(input)
  var sigs = List.empty[String]
  var sig = graph.map(_.mkString).mkString("\n")
  while (!sigs.contains(sig)) {
    sigs = sig :: sigs
    Range(0, 4)
      .foreach(_ => {
        val columnLength = graph.length
        graph = graph.transpose
          .map(column =>
            column.mkString
              .split("#")
              .map(elm => ("O" * elm.count(_ == 'O')).padTo(elm.length, '.'))
              .mkString("#")
              .padTo(columnLength, '#')
              .reverse
              .toArray
          )
      })
    sig = graph.map(_.mkString).mkString("\n")
  }
  sigs = sigs.reverse
  val start = sigs.indexOf(sig)
  val loopSize = sigs.length - start
  parse(
    sigs(start + (1_000_000_000 - start) % loopSize)
  ).transpose
    .map(column =>
      column.reverse.zipWithIndex
        .map((c, i) => if (c == 'O') i + 1 else 0)
        .sum
    )
    .sum
}
